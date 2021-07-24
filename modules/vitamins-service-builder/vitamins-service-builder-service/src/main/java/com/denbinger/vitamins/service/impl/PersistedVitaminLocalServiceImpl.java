/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.denbinger.vitamins.service.impl;

import com.denbinger.vitamins.constants.VitaminDetailType;
import com.denbinger.vitamins.exception.NoSuchPersistedVitaminException;
import com.denbinger.vitamins.model.PersistedVitamin;
import com.denbinger.vitamins.service.PersistedVitaminLocalService;
import com.denbinger.vitamins.service.VitaminDetailLocalService;
import com.denbinger.vitamins.service.base.PersistedVitaminLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.Index;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.security.service.access.policy.constants.SAPEntryConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * The implementation of the persisted vitamin local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.dnebinger.vitamins.service.PersistedVitaminLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedVitaminLocalServiceBaseImpl
 */
@Component(
        property = "model.class.name=com.dnebinger.vitamins.model.PersistedVitamin",
        service = AopService.class
)
public class PersistedVitaminLocalServiceImpl
        extends PersistedVitaminLocalServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Use <code>com.dnebinger.vitamins.service.PersistedVitaminLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.dnebinger.vitamins.service.PersistedVitaminLocalServiceUtil</code>.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistedVitaminLocalService.class);

    private VitaminDetailLocalService vitaminDetailLocalService;

    @Reference(unbind = "-")
    protected void setVitaminDetailLocalService(VitaminDetailLocalService vitaminDetailLocalService) {
        this.vitaminDetailLocalService = vitaminDetailLocalService;
    }

    public PersistedVitamin getPersistedVitamin(String surrogateId) {
        return persistedVitaminPersistence.fetchBySurrogateId(surrogateId);
    }


    @SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
    @Indexable(type = IndexableType.REINDEX)
    public PersistedVitamin addPersistedVitamin(String id, String name, String groupName, String description, int typeCode,
                                                String articleId, String[] chemicalNames, String[] properties, String[] attributes,
                                                String[] symptoms, String[] risks, ServiceContext serviceContext) throws PortalException {
        PersistedVitamin vitamin = createPersistedVitamin(counterLocalService.increment(PersistedVitamin.class.getName()));

        Date current = new Date();

        String surrogateId = null;

        if ((id == null) || (id.trim().length() < 1))
            surrogateId = RandomStringUtils.random(10, true, true).toUpperCase();
        else
            surrogateId = id.trim().toUpperCase();

        vitamin.setArticleId(articleId);
        vitamin.setCompanyId(serviceContext.getCompanyId());
        vitamin.setCreateDate(serviceContext.getCreateDate(current));
        vitamin.setDescription(description);
        vitamin.setGroupId(serviceContext.getScopeGroupId());
        vitamin.setGroupName(groupName);
        vitamin.setName(name);
        vitamin.setSurrogateId(surrogateId);
        vitamin.setModifiedDate(serviceContext.getModifiedDate(current));
        vitamin.setType(typeCode);
        vitamin.setUserId(serviceContext.getUserId());

        User user = userLocalService.fetchUser(serviceContext.getUserId());

        if (user != null) {
            vitamin.setUserName(user.getFullName());
            vitamin.setUserUuid(user.getUuid());
        }


        //add the details ...

        addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.CHEMICAL_NAME, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), properties, VitaminDetailType.PROPERTY, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), attributes, VitaminDetailType.ATTRIBUTE, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), symptoms, VitaminDetailType.SYMPTOM, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), risks, VitaminDetailType.RISK, serviceContext);

        resourceLocalService.addResources(vitamin.getCompanyId(), vitamin.getGroupId(),
                vitamin.getUserId(), PersistedVitamin.class.getName(),
                vitamin.getPersistedVitaminId(), false,
                serviceContext.isAddGroupPermissions(), serviceContext.isAddGuestPermissions());

        return vitamin;
    }

    @Indexable(type = IndexableType.REINDEX)
    @SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
    public PersistedVitamin updatePersistedVitamin(String oldId, String id, String name, String groupName, String description,
                                                   int typeCode, String articleId, String[] chemicalNames, String[] properties,
                                                   String[] attributes, String[] symptoms, String[] risks, ServiceContext serviceContext) throws PortalException {
        PersistedVitamin vitamin = persistedVitaminPersistence.fetchBySurrogateId(oldId);

        if (vitamin == null) {
            LOGGER.warn("Failed to find vitamin using id: " + oldId + " .");
            throw new NoSuchPersistedVitaminException("Could not find vitamin[" + oldId + "].");
        }

        String surrogateId = null;

        if ((id == null) || (id.trim().length() < 1))
            surrogateId = RandomStringUtils.random(10, true, true).toUpperCase();
        else
            surrogateId = id.trim().toUpperCase();

        Date date = new Date();

        // update means all filed should change and match what we are given.

        vitamin.setArticleId(articleId);
        vitamin.setDescription(description);
        vitamin.setGroupName(groupName);
        vitamin.setSurrogateId(surrogateId);
        vitamin.setName(name);
        vitamin.setModifiedDate(serviceContext.getModifiedDate(date));
        vitamin.setType(typeCode);
        vitamin.setUserId(serviceContext.getUserId());

        User user = userLocalService.fetchUser(serviceContext.getUserId());
        if (user != null) {
            vitamin.setUserName(user.getFullName());
            vitamin.setUserUuid(user.getUserUuid());
        }

        vitamin = updatePersistedVitamin(vitamin);


        //delete all vitamin's details
        vitaminDetailLocalService.deleteAllVitaminDetails(vitamin.getPersistedVitaminId());

        //add the details ...

        addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.CHEMICAL_NAME, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), properties, VitaminDetailType.PROPERTY, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), attributes, VitaminDetailType.ATTRIBUTE, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), symptoms, VitaminDetailType.SYMPTOM, serviceContext);
        addDetail(vitamin.getPersistedVitaminId(), risks, VitaminDetailType.RISK, serviceContext);

        return vitamin;
    }


    @Indexable(type = IndexableType.REINDEX)
    @SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
    public PersistedVitamin patchPersistedVitamin(String oldId, String id, String name, String groupName, String description,
                                                  int typeCode, String articleId, String[] chemicalNames, String[] properties,
                                                  String[] attributes, String[] symptoms, String[] risks, ServiceContext serviceContext) throws PortalException {
        PersistedVitamin vitamin = persistedVitaminPersistence.fetchBySurrogateId(oldId);

        if (vitamin == null) {
            LOGGER.warn("Failed to find vitamin using id: " + oldId + " .");
            throw new NoSuchPersistedVitaminException("Could not find vitamin[" + oldId + "].");
        }


        // a patch means that only provided fields are going to change to match what we are given.

        boolean changed = false;

        if (id != null && id.trim().length() > 0) {
            vitamin.setSurrogateId(id);
            changed = true;
        }

        if (name != null) {
            vitamin.setName(name);
            changed = true;
        }
        if (groupName != null) {
            vitamin.setGroupName(groupName);
            changed = true;
        }
        if (description != null) {
            vitamin.setDescription(description);
            changed = true;
        }
        if (typeCode != vitamin.getType()) {
            vitamin.setType(typeCode);
            changed = true;
        }

        if (articleId != null) {
            vitamin.setArticleId(articleId);
            changed = true;
        }

        if (chemicalNames != null && chemicalNames.length > 0) {
            vitaminDetailLocalService.deleteVitaminDetailByType(vitamin.getPersistedVitaminId(), VitaminDetailType.CHEMICAL_NAME);
            changed = true;
            addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.CHEMICAL_NAME, serviceContext);
        }
        if (properties != null && properties.length > 0) {
            vitaminDetailLocalService.deleteVitaminDetailByType(vitamin.getPersistedVitaminId(), VitaminDetailType.PROPERTY);
            changed = true;
            addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.PROPERTY, serviceContext);
        }
        if (attributes != null && attributes.length > 0) {
            vitaminDetailLocalService.deleteVitaminDetailByType(vitamin.getPersistedVitaminId(), VitaminDetailType.ATTRIBUTE);
            changed = true;
            addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.ATTRIBUTE, serviceContext);
        }
        if (risks != null && risks.length > 0) {
            vitaminDetailLocalService.deleteVitaminDetailByType(vitamin.getPersistedVitaminId(), VitaminDetailType.RISK);
            changed = true;
            addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.RISK, serviceContext);
        }
        if (symptoms != null && symptoms.length > 0) {
            vitaminDetailLocalService.deleteVitaminDetailByType(vitamin.getPersistedVitaminId(), VitaminDetailType.SYMPTOM);
            changed = true;
            addDetail(vitamin.getPersistedVitaminId(), chemicalNames, VitaminDetailType.SYMPTOM, serviceContext);
        }

        if (changed) {
            Date date = new Date();

            vitamin.setUserId(serviceContext.getUserId());
            vitamin.setModifiedDate(serviceContext.getModifiedDate(date));

            User user = userLocalService.fetchUser(serviceContext.getUserId());
            if (user != null) {
                vitamin.setUserName(user.getFullName());
                vitamin.setUserUuid(user.getUserUuid());
            }

            vitamin = updatePersistedVitamin(vitamin);
        }
        return vitamin;
    }

    @Indexable(type = IndexableType.DELETE)
    public void deleteVitamin(String surrogateId) throws PortalException {
        PersistedVitamin vitamin = getPersistedVitamin(surrogateId);
        if (vitamin != null) {
            deletePersistedVitamin(vitamin);
        }
    }

    @Indexable(type = IndexableType.DELETE)
    public PersistedVitamin deleteVitamin(long persistedVitaminId) {
        PersistedVitamin vitamin = fetchPersistedVitamin(persistedVitaminId);
        if (vitamin != null)
            deletePersistedVitamin(vitamin);
        return vitamin;
    }

    @Indexable(type = IndexableType.DELETE)
    @SystemEvent(type = SystemEventConstants.TYPE_DELETE)
    @Override
    public PersistedVitamin deletePersistedVitamin(PersistedVitamin persistedVitamin) {
        vitaminDetailLocalService.deleteAllVitaminDetails(persistedVitamin.getPersistedVitaminId());
        return persistedVitaminLocalService.deletePersistedVitamin(persistedVitamin);
    }

    private void addDetail(long persistedVitaminId, String[] data, int detailType, ServiceContext serviceContext) throws PortalException {
        if (data != null && data.length > 0) {
            for (String value : data) {
                vitaminDetailLocalService.addVitaminDetail(persistedVitaminId, detailType, value, serviceContext);
            }
        }
    }
}