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
 * Provides the remote service utility for PersistedVitamin. This utility wraps
 * <code>com.denbinger.vitamins.service.impl.PersistedVitaminServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see PersistedVitaminService
 * @generated
 */
public class PersistedVitaminServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.denbinger.vitamins.service.impl.PersistedVitaminServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.denbinger.vitamins.model.PersistedVitamin
			addPersistedVitamin(
				String id, String name, String groupName, String description,
				int typeCode, String articleId, String[] chemicalNames,
				String[] properties, String[] attributes, String[] symptoms,
				String[] risks,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addPersistedVitamin(
			id, name, groupName, description, typeCode, articleId,
			chemicalNames, properties, attributes, symptoms, risks,
			serviceContext);
	}

	public static void deletePersistedVitamin(String surrogateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deletePersistedVitamin(surrogateId);
	}

	public static java.util.List<com.denbinger.vitamins.model.PersistedVitamin>
		getAll() {

		return getService().getAll();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.denbinger.vitamins.model.PersistedVitamin
			getPersistedVitamin(long persistedVitaminId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedVitamin(persistedVitaminId);
	}

	public static com.denbinger.vitamins.model.PersistedVitamin
			getPersistedVitamin(String surrogateId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedVitamin(surrogateId);
	}

	public static com.denbinger.vitamins.model.PersistedVitamin
			patchPersistedVitamin(
				String oldId, String id, String name, String groupName,
				String description, int typeCode, String articleId,
				String[] chemicalNames, String[] properties,
				String[] attributes, String[] symptoms, String[] risks,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().patchPersistedVitamin(
			oldId, id, name, groupName, description, typeCode, articleId,
			chemicalNames, properties, attributes, symptoms, risks,
			serviceContext);
	}

	public static com.denbinger.vitamins.model.PersistedVitamin
			updatePersistedVitamin(
				String oldId, String id, String name, String groupName,
				String description, int typeCode, String articleId,
				String[] chemicalNames, String[] properties,
				String[] attributes, String[] symptoms, String[] risks,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updatePersistedVitamin(
			oldId, id, name, groupName, description, typeCode, articleId,
			chemicalNames, properties, attributes, symptoms, risks,
			serviceContext);
	}

	public static PersistedVitaminService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<PersistedVitaminService, PersistedVitaminService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PersistedVitaminService.class);

		ServiceTracker<PersistedVitaminService, PersistedVitaminService>
			serviceTracker =
				new ServiceTracker
					<PersistedVitaminService, PersistedVitaminService>(
						bundle.getBundleContext(),
						PersistedVitaminService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}