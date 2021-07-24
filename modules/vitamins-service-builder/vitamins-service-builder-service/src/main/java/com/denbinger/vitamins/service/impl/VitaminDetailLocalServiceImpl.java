/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.denbinger.vitamins.service.impl;

import com.denbinger.vitamins.model.VitaminDetail;
import com.denbinger.vitamins.service.VitaminDetailLocalService;
import com.denbinger.vitamins.service.base.VitaminDetailLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the vitamin detail local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.dnebinger.vitamins.service.VitaminDetailLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VitaminDetailLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.dnebinger.vitamins.model.VitaminDetail",
	service = AopService.class
)
public class VitaminDetailLocalServiceImpl
	extends VitaminDetailLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.dnebinger.vitamins.service.VitaminDetailLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.dnebinger.vitamins.service.VitaminDetailLocalServiceUtil</code>.
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(VitaminDetailLocalService.class);

	public VitaminDetail addVitaminDetail(long persistedVitaminId, int typeCode, String value, ServiceContext serviceContext) throws PortalException {
		VitaminDetail vitaminDetail = vitaminDetailPersistence.create(persistedVitaminId);
		vitaminDetail.setType(typeCode);
		vitaminDetail.setValue(value);

		Date now = new Date();

		vitaminDetail.setCompanyId(serviceContext.getCompanyId());
		vitaminDetail.setCreateDate(serviceContext.getCreateDate(now));
		vitaminDetail.setGroupId(serviceContext.getScopeGroupId());
		vitaminDetail.setModifiedDate(serviceContext.getModifiedDate(now));
		vitaminDetail.setUserId(serviceContext.getUserId());

		User user = userLocalService.getUser(serviceContext.getUserId());

		if (user!=null){
			vitaminDetail.setUserName(user.getFullName());
			vitaminDetail.setUserUuid(user.getUuid());
		}

		vitaminDetail = addVitaminDetail(vitaminDetail);

		return vitaminDetail;
	}

	public void deleteAllVitaminDetails(long persistedVitaminId){
		List<VitaminDetail> details = vitaminDetailPersistence.findByPersistedVitaminId(persistedVitaminId);

		if (details!=null && !details.isEmpty())
			for (VitaminDetail detail : details) {
				deleteVitaminDetail(detail);
			}

	}

	public void deleteVitaminDetailByType(long persistedVitaminId, int detailType){
		List<VitaminDetail> details = vitaminDetailPersistence.findByPersistedVitaminIdType(persistedVitaminId, detailType);
		if (details!=null && !details.isEmpty())
			for (VitaminDetail detail : details) {
				deleteVitaminDetail(detail);
			}
	}

	@Override
	public VitaminDetail deleteVitaminDetail(long vitaminDetailId) throws PortalException {
		VitaminDetail vitaminDetail = fetchVitaminDetail(vitaminDetailId);
		if (vitaminDetail!=null){}
			resourceLocalService.deleteResource(vitaminDetail.getCompanyId(), VitaminDetail.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL,vitaminDetail.getVitaminDetailId());
		return super.deleteVitaminDetail(vitaminDetailId);
	}

	@Override
	public VitaminDetail deleteVitaminDetail(VitaminDetail vitaminDetail) {
		try {
			resourceLocalService.deleteResource(
					vitaminDetail.getCompanyId(), VitaminDetail.class.getName(),
					ResourceConstants.SCOPE_INDIVIDUAL, vitaminDetail.getVitaminDetailId());
		} catch (PortalException e) {
			LOGGER.warn("Error deleting vitamin detail permissions: " + e.getMessage(), e);
		}
		return super.deleteVitaminDetail(vitaminDetail);
	}


	public List<VitaminDetail> getAllVitaminDetails(long persistedVitaminId){
		return vitaminDetailPersistence.findByPersistedVitaminId(persistedVitaminId);
	}

	public List<VitaminDetail> getVitaminDetailsByType(long persistedVitaminId, int typeCode){
		return vitaminDetailPersistence.findByPersistedVitaminIdType(persistedVitaminId, typeCode);
	}
}