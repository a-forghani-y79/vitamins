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

package com.dnebinger.vitamins.model.impl;

import com.dnebinger.vitamins.model.VitaminDetail;
import com.dnebinger.vitamins.model.VitaminDetailModel;
import com.dnebinger.vitamins.model.VitaminDetailSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the VitaminDetail service. Represents a row in the &quot;NEB_VitaminDetail&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>VitaminDetailModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VitaminDetailImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VitaminDetailImpl
 * @generated
 */
@JSON(strict = true)
public class VitaminDetailModelImpl
	extends BaseModelImpl<VitaminDetail> implements VitaminDetailModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vitamin detail model instance should use the <code>VitaminDetail</code> interface instead.
	 */
	public static final String TABLE_NAME = "NEB_VitaminDetail";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"vitaminDetailId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"persistedVitaminId", Types.BIGINT}, {"type_", Types.INTEGER},
		{"value", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("vitaminDetailId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("persistedVitaminId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("value", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table NEB_VitaminDetail (uuid_ VARCHAR(75) null,vitaminDetailId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,persistedVitaminId LONG,type_ INTEGER,value VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table NEB_VitaminDetail";

	public static final String ORDER_BY_JPQL =
		" ORDER BY vitaminDetail.type ASC, vitaminDetail.value ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY NEB_VitaminDetail.type_ ASC, NEB_VitaminDetail.value ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long PERSISTEDVITAMINID_COLUMN_BITMASK = 4L;

	public static final long TYPE_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long VALUE_COLUMN_BITMASK = 32L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static VitaminDetail toModel(VitaminDetailSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		VitaminDetail model = new VitaminDetailImpl();

		model.setUuid(soapModel.getUuid());
		model.setVitaminDetailId(soapModel.getVitaminDetailId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setPersistedVitaminId(soapModel.getPersistedVitaminId());
		model.setType(soapModel.getType());
		model.setValue(soapModel.getValue());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<VitaminDetail> toModels(VitaminDetailSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<VitaminDetail> models = new ArrayList<VitaminDetail>(
			soapModels.length);

		for (VitaminDetailSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public VitaminDetailModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _vitaminDetailId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setVitaminDetailId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vitaminDetailId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return VitaminDetail.class;
	}

	@Override
	public String getModelClassName() {
		return VitaminDetail.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<VitaminDetail, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<VitaminDetail, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VitaminDetail, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((VitaminDetail)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<VitaminDetail, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<VitaminDetail, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(VitaminDetail)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<VitaminDetail, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<VitaminDetail, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, VitaminDetail>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			VitaminDetail.class.getClassLoader(), VitaminDetail.class,
			ModelWrapper.class);

		try {
			Constructor<VitaminDetail> constructor =
				(Constructor<VitaminDetail>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<VitaminDetail, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<VitaminDetail, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<VitaminDetail, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<VitaminDetail, Object>>();
		Map<String, BiConsumer<VitaminDetail, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<VitaminDetail, ?>>();

		attributeGetterFunctions.put("uuid", VitaminDetail::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<VitaminDetail, String>)VitaminDetail::setUuid);
		attributeGetterFunctions.put(
			"vitaminDetailId", VitaminDetail::getVitaminDetailId);
		attributeSetterBiConsumers.put(
			"vitaminDetailId",
			(BiConsumer<VitaminDetail, Long>)VitaminDetail::setVitaminDetailId);
		attributeGetterFunctions.put("groupId", VitaminDetail::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<VitaminDetail, Long>)VitaminDetail::setGroupId);
		attributeGetterFunctions.put("companyId", VitaminDetail::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<VitaminDetail, Long>)VitaminDetail::setCompanyId);
		attributeGetterFunctions.put("userId", VitaminDetail::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<VitaminDetail, Long>)VitaminDetail::setUserId);
		attributeGetterFunctions.put("userName", VitaminDetail::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<VitaminDetail, String>)VitaminDetail::setUserName);
		attributeGetterFunctions.put(
			"createDate", VitaminDetail::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<VitaminDetail, Date>)VitaminDetail::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", VitaminDetail::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<VitaminDetail, Date>)VitaminDetail::setModifiedDate);
		attributeGetterFunctions.put(
			"persistedVitaminId", VitaminDetail::getPersistedVitaminId);
		attributeSetterBiConsumers.put(
			"persistedVitaminId",
			(BiConsumer<VitaminDetail, Long>)
				VitaminDetail::setPersistedVitaminId);
		attributeGetterFunctions.put("type", VitaminDetail::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<VitaminDetail, Integer>)VitaminDetail::setType);
		attributeGetterFunctions.put("value", VitaminDetail::getValue);
		attributeSetterBiConsumers.put(
			"value",
			(BiConsumer<VitaminDetail, String>)VitaminDetail::setValue);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getVitaminDetailId() {
		return _vitaminDetailId;
	}

	@Override
	public void setVitaminDetailId(long vitaminDetailId) {
		_vitaminDetailId = vitaminDetailId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getPersistedVitaminId() {
		return _persistedVitaminId;
	}

	@Override
	public void setPersistedVitaminId(long persistedVitaminId) {
		_columnBitmask |= PERSISTEDVITAMINID_COLUMN_BITMASK;

		if (!_setOriginalPersistedVitaminId) {
			_setOriginalPersistedVitaminId = true;

			_originalPersistedVitaminId = _persistedVitaminId;
		}

		_persistedVitaminId = persistedVitaminId;
	}

	public long getOriginalPersistedVitaminId() {
		return _originalPersistedVitaminId;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public String getValue() {
		if (_value == null) {
			return "";
		}
		else {
			return _value;
		}
	}

	@Override
	public void setValue(String value) {
		_value = value;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(VitaminDetail.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), VitaminDetail.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public VitaminDetail toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, VitaminDetail>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		VitaminDetailImpl vitaminDetailImpl = new VitaminDetailImpl();

		vitaminDetailImpl.setUuid(getUuid());
		vitaminDetailImpl.setVitaminDetailId(getVitaminDetailId());
		vitaminDetailImpl.setGroupId(getGroupId());
		vitaminDetailImpl.setCompanyId(getCompanyId());
		vitaminDetailImpl.setUserId(getUserId());
		vitaminDetailImpl.setUserName(getUserName());
		vitaminDetailImpl.setCreateDate(getCreateDate());
		vitaminDetailImpl.setModifiedDate(getModifiedDate());
		vitaminDetailImpl.setPersistedVitaminId(getPersistedVitaminId());
		vitaminDetailImpl.setType(getType());
		vitaminDetailImpl.setValue(getValue());

		vitaminDetailImpl.resetOriginalValues();

		return vitaminDetailImpl;
	}

	@Override
	public int compareTo(VitaminDetail vitaminDetail) {
		int value = 0;

		if (getType() < vitaminDetail.getType()) {
			value = -1;
		}
		else if (getType() > vitaminDetail.getType()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getValue().compareTo(vitaminDetail.getValue());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof VitaminDetail)) {
			return false;
		}

		VitaminDetail vitaminDetail = (VitaminDetail)object;

		long primaryKey = vitaminDetail.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		_originalUuid = _uuid;

		_originalGroupId = _groupId;

		_setOriginalGroupId = false;

		_originalCompanyId = _companyId;

		_setOriginalCompanyId = false;

		_setModifiedDate = false;
		_originalPersistedVitaminId = _persistedVitaminId;

		_setOriginalPersistedVitaminId = false;

		_originalType = _type;

		_setOriginalType = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<VitaminDetail> toCacheModel() {
		VitaminDetailCacheModel vitaminDetailCacheModel =
			new VitaminDetailCacheModel();

		vitaminDetailCacheModel.uuid = getUuid();

		String uuid = vitaminDetailCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			vitaminDetailCacheModel.uuid = null;
		}

		vitaminDetailCacheModel.vitaminDetailId = getVitaminDetailId();

		vitaminDetailCacheModel.groupId = getGroupId();

		vitaminDetailCacheModel.companyId = getCompanyId();

		vitaminDetailCacheModel.userId = getUserId();

		vitaminDetailCacheModel.userName = getUserName();

		String userName = vitaminDetailCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			vitaminDetailCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			vitaminDetailCacheModel.createDate = createDate.getTime();
		}
		else {
			vitaminDetailCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			vitaminDetailCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			vitaminDetailCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		vitaminDetailCacheModel.persistedVitaminId = getPersistedVitaminId();

		vitaminDetailCacheModel.type = getType();

		vitaminDetailCacheModel.value = getValue();

		String value = vitaminDetailCacheModel.value;

		if ((value != null) && (value.length() == 0)) {
			vitaminDetailCacheModel.value = null;
		}

		return vitaminDetailCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<VitaminDetail, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<VitaminDetail, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VitaminDetail, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((VitaminDetail)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<VitaminDetail, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<VitaminDetail, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VitaminDetail, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((VitaminDetail)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, VitaminDetail>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _vitaminDetailId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _persistedVitaminId;
	private long _originalPersistedVitaminId;
	private boolean _setOriginalPersistedVitaminId;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private String _value;
	private long _columnBitmask;
	private VitaminDetail _escapedModel;

}