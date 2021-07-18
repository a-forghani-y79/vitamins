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

package com.dnebinger.vitamins.service.base;

import com.dnebinger.vitamins.model.PersistedVitamin;
import com.dnebinger.vitamins.service.PersistedVitaminService;
import com.dnebinger.vitamins.service.persistence.PersistedVitaminPersistence;
import com.dnebinger.vitamins.service.persistence.VitaminDetailPersistence;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the persisted vitamin remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.dnebinger.vitamins.service.impl.PersistedVitaminServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.dnebinger.vitamins.service.impl.PersistedVitaminServiceImpl
 * @generated
 */
public abstract class PersistedVitaminServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, IdentifiableOSGiService, PersistedVitaminService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PersistedVitaminService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.dnebinger.vitamins.service.PersistedVitaminServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			PersistedVitaminService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		persistedVitaminService = (PersistedVitaminService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PersistedVitaminService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PersistedVitamin.class;
	}

	protected String getModelClassName() {
		return PersistedVitamin.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = persistedVitaminPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Reference
	protected com.dnebinger.vitamins.service.PersistedVitaminLocalService
		persistedVitaminLocalService;

	protected PersistedVitaminService persistedVitaminService;

	@Reference
	protected PersistedVitaminPersistence persistedVitaminPersistence;

	@Reference
	protected VitaminDetailPersistence vitaminDetailPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

}