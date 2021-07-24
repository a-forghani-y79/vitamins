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

import com.denbinger.vitamins.model.PersistedVitamin;
import com.denbinger.vitamins.service.base.PersistedVitaminServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import java.util.List;

/**
 * The implementation of the persisted vitamin remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.dnebinger.vitamins.service.PersistedVitaminService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PersistedVitaminServiceBaseImpl
 */
@Component(
        property = {
                "json.web.service.context.name=neb",
                "json.web.service.context.path=PersistedVitamin"
        },
        service = AopService.class
)
public class PersistedVitaminServiceImpl
        extends PersistedVitaminServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use <code>com.dnebinger.vitamins.service.PersistedVitaminServiceUtil</code> to access the persisted vitamin remote service.
     */

    @Reference(
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY,
            target = "(model.class.name=com.denbinger.vitamins.model.PersistedVitamin)"
    )
    private volatile ModelResourcePermission<PersistedVitamin> persistedVitaminModelResourcePermission;

    public PersistedVitamin getPersistedVitamin(String surrogateId) throws PortalException {
        PersistedVitamin vitamin = persistedVitaminLocalService.getPersistedVitamin(surrogateId);

        persistedVitaminModelResourcePermission.check(getPermissionChecker(), vitamin, ActionKeys.VIEW);
        return vitamin;
    }

    public PersistedVitamin getPersistedVitamin(final long persistedVitaminId) throws PortalException {
        PersistedVitamin vitamin = persistedVitaminLocalService.getPersistedVitamin(
                persistedVitaminId);

        persistedVitaminModelResourcePermission.check(
                getPermissionChecker(), vitamin, ActionKeys.VIEW);

        return vitamin;
    }

    public PersistedVitamin addPersistedVitamin(String id, String name, String groupName, String description, int typeCode,
                                                String articleId, String[] chemicalNames, String[] properties, String[] attributes,
                                                String[] symptoms, String[] risks, ServiceContext serviceContext) throws PortalException {
        ModelResourcePermissionHelper.check(persistedVitaminModelResourcePermission, getPermissionChecker(),
                serviceContext.getScopeGroupId(), 0, ActionKeys.ADD_ENTRY);
        return persistedVitaminLocalService.addPersistedVitamin(id, name, groupName, description, typeCode, articleId,
                chemicalNames, properties, attributes, symptoms, risks, serviceContext);
    }

    public PersistedVitamin updatePersistedVitamin(String oldId, String id, String name, String groupName, String description,
                                                   int typeCode, String articleId, String[] chemicalNames, String[] properties,
                                                   String[] attributes, String[] symptoms, String[] risks, ServiceContext serviceContext) throws PortalException {
        persistedVitaminModelResourcePermission.check(getPermissionChecker(),
                persistedVitaminLocalService.getPersistedVitamin(oldId), ActionKeys.UPDATE);
        return persistedVitaminLocalService.updatePersistedVitamin(oldId, id, name, groupName, description, typeCode, articleId,
                chemicalNames, properties, attributes, symptoms, risks, serviceContext);
    }

    public PersistedVitamin patchPersistedVitamin(String oldId, String id, String name, String groupName, String description,
                                                  int typeCode, String articleId, String[] chemicalNames, String[] properties,
                                                  String[] attributes, String[] symptoms,
                                                  String[] risks, ServiceContext serviceContext) throws PortalException {
        persistedVitaminModelResourcePermission.check(getPermissionChecker(),
                persistedVitaminLocalService.getPersistedVitamin(oldId),
                ActionKeys.UPDATE);
        return persistedVitaminLocalService.patchPersistedVitamin(oldId, id, name, groupName, description, typeCode, articleId,
                chemicalNames, properties, attributes, symptoms, risks, serviceContext);
    }

    public void deletePersistedVitamin(String surrogateId) throws PortalException {
        persistedVitaminModelResourcePermission.check(getPermissionChecker(),
                persistedVitaminLocalService.getPersistedVitamin(surrogateId), ActionKeys.DELETE);
        persistedVitaminLocalService.deleteVitamin(surrogateId);
    }

    public List<PersistedVitamin> getAll(){
        return persistedVitaminPersistence.findAll();
    }
}