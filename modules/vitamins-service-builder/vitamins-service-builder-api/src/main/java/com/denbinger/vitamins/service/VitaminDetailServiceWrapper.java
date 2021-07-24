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

package com.denbinger.vitamins.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VitaminDetailService}.
 *
 * @author Brian Wing Shun Chan
 * @see VitaminDetailService
 * @generated
 */
public class VitaminDetailServiceWrapper
	implements ServiceWrapper<VitaminDetailService>, VitaminDetailService {

	public VitaminDetailServiceWrapper(
		VitaminDetailService vitaminDetailService) {

		_vitaminDetailService = vitaminDetailService;
	}

	@Override
	public com.denbinger.vitamins.model.VitaminDetail addVitaminDetail(
			long persistedVitaminId, int typeCode, String value,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vitaminDetailService.addVitaminDetail(
			persistedVitaminId, typeCode, value, serviceContext);
	}

	@Override
	public void deleteAllVitaminDetails(long persistedVitaminId) {
		_vitaminDetailService.deleteAllVitaminDetails(persistedVitaminId);
	}

	@Override
	public com.denbinger.vitamins.model.VitaminDetail deleteVitaminDetail(
			long vitaminDetailId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _vitaminDetailService.deleteVitaminDetail(vitaminDetailId);
	}

	@Override
	public com.denbinger.vitamins.model.VitaminDetail deleteVitaminDetail(
		com.denbinger.vitamins.model.VitaminDetail vitaminDetail) {

		return _vitaminDetailService.deleteVitaminDetail(vitaminDetail);
	}

	@Override
	public void deleteVitaminDetailsByType(
		long persistedVitaminId, int typeCode) {

		_vitaminDetailService.deleteVitaminDetailsByType(
			persistedVitaminId, typeCode);
	}

	@Override
	public java.util.List<com.denbinger.vitamins.model.VitaminDetail>
		getAllVitaminDetails(long persistedVitaminId) {

		return _vitaminDetailService.getAllVitaminDetails(persistedVitaminId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _vitaminDetailService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.denbinger.vitamins.model.VitaminDetail>
		getVitaminDetailsByType(long persistedVitaminId, int typeCode) {

		return _vitaminDetailService.getVitaminDetailsByType(
			persistedVitaminId, typeCode);
	}

	@Override
	public VitaminDetailService getWrappedService() {
		return _vitaminDetailService;
	}

	@Override
	public void setWrappedService(VitaminDetailService vitaminDetailService) {
		_vitaminDetailService = vitaminDetailService;
	}

	private VitaminDetailService _vitaminDetailService;

}