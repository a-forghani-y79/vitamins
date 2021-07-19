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

package com.dnebinger.vitamins.service.impl;

import com.dnebinger.vitamins.model.VitaminDetail;
import com.dnebinger.vitamins.service.base.VitaminDetailServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionHelper;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The implementation of the vitamin detail remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.dnebinger.vitamins.service.VitaminDetailService</code> interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VitaminDetailServiceBaseImpl
 */
@Component(
        property = {
                "json.web.service.context.name=neb",
                "json.web.service.context.path=VitaminDetail"
        },
        service = AopService.class
)
public class VitaminDetailServiceImpl extends VitaminDetailServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use <code>com.dnebinger.vitamins.service.VitaminDetailServiceUtil</code> to access the vitamin detail remote service.
     */

    @Reference(
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY,
            target = "(model.class.name=com.denbinger.vitamins.model.VitaminDetail)"
    )
    private volatile ModelResourcePermission<VitaminDetail> vitaminDetailModelResourcePermission;

    private final static Logger LOGGER = LoggerFactory.getLogger(VitaminDetailServiceImpl.class);

    public VitaminDetail addVitaminDetail(long persistedVitaminId,
                                          int typeCode, String value,
                                          ServiceContext serviceContext) throws PortalException {
        ModelResourcePermissionHelper.check(vitaminDetailModelResourcePermission,
                getPermissionChecker(), serviceContext.getScopeGroupId(), 0, ActionKeys.ADD_ENTRY);


        return vitaminDetailLocalService.addVitaminDetail(persistedVitaminId, typeCode, value, serviceContext);
    }

    public void deleteAllVitaminDetails(long persistedVitaminId) {
        LOGGER.warn("delete all vitamin details line 81");
        vitaminDetailLocalService.deleteAllVitaminDetails(persistedVitaminId);
    }

    public void deleteVitaminDetailsByType(final long persistedVitaminId,
                                           final int typeCode) {
        LOGGER.warn("delete all vitamin details by type line 88");
        vitaminDetailLocalService.deleteVitaminDetailByType(persistedVitaminId, typeCode);
    }

    public VitaminDetail deleteVitaminDetail(long vitaminDetailId)
            throws PortalException {

        return vitaminDetailLocalService.deleteVitaminDetail(vitaminDetailId);
    }

    public VitaminDetail deleteVitaminDetail(VitaminDetail vitaminDetail) {

        return deleteVitaminDetail(vitaminDetail);
    }

    public List<VitaminDetail> getAllVitaminDetails(final long persistedVitaminId) {
        return vitaminDetailPersistence.findByPersistedVitaminId(persistedVitaminId);
    }

    public List<VitaminDetail> getVitaminDetailsByType(final long persistedVitaminId,
                                                       final int typeCode) {
        return vitaminDetailPersistence.findByPersistedVitaminIdType(persistedVitaminId, typeCode);
    }

}