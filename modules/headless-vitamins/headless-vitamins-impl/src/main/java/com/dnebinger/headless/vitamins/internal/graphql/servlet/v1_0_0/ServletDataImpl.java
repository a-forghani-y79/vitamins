package com.dnebinger.headless.vitamins.internal.graphql.servlet.v1_0_0;

import com.dnebinger.headless.vitamins.internal.graphql.mutation.v1_0_0.Mutation;
import com.dnebinger.headless.vitamins.internal.graphql.query.v1_0_0.Query;
import com.dnebinger.headless.vitamins.resource.v1_0_0.VitaminResource;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;



import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

import javax.annotation.processing.Generated;

/**
 * @author Dave Nebinger
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setVitaminResourceComponentServiceObjects(
			_vitaminResourceComponentServiceObjects);

		Query.setVitaminResourceComponentServiceObjects(
			_vitaminResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/headless-vitamins-graphql/v1_0_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<VitaminResource>
		_vitaminResourceComponentServiceObjects;

}