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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for VitaminDetail. This utility wraps
 * <code>com.denbinger.vitamins.service.impl.VitaminDetailServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see VitaminDetailService
 * @generated
 */
public class VitaminDetailServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.denbinger.vitamins.service.impl.VitaminDetailServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.denbinger.vitamins.model.VitaminDetail addVitaminDetail(
			long persistedVitaminId, int typeCode, String value,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addVitaminDetail(
			persistedVitaminId, typeCode, value, serviceContext);
	}

	public static void deleteAllVitaminDetails(long persistedVitaminId) {
		getService().deleteAllVitaminDetails(persistedVitaminId);
	}

	public static com.denbinger.vitamins.model.VitaminDetail
			deleteVitaminDetail(long vitaminDetailId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteVitaminDetail(vitaminDetailId);
	}

	public static com.denbinger.vitamins.model.VitaminDetail
		deleteVitaminDetail(
			com.denbinger.vitamins.model.VitaminDetail vitaminDetail) {

		return getService().deleteVitaminDetail(vitaminDetail);
	}

	public static void deleteVitaminDetailsByType(
		long persistedVitaminId, int typeCode) {

		getService().deleteVitaminDetailsByType(persistedVitaminId, typeCode);
	}

	public static java.util.List<com.denbinger.vitamins.model.VitaminDetail>
		getAllVitaminDetails(long persistedVitaminId) {

		return getService().getAllVitaminDetails(persistedVitaminId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.denbinger.vitamins.model.VitaminDetail>
		getVitaminDetailsByType(long persistedVitaminId, int typeCode) {

		return getService().getVitaminDetailsByType(
			persistedVitaminId, typeCode);
	}

	public static VitaminDetailService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VitaminDetailService, VitaminDetailService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VitaminDetailService.class);

		ServiceTracker<VitaminDetailService, VitaminDetailService>
			serviceTracker =
				new ServiceTracker<VitaminDetailService, VitaminDetailService>(
					bundle.getBundleContext(), VitaminDetailService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}