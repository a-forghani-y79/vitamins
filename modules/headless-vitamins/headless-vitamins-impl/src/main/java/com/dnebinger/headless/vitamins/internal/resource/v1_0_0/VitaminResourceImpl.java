package com.dnebinger.headless.vitamins.internal.resource.v1_0_0;

import com.dnebinger.headless.vitamins.dto.v1_0_0.Vitamin;
import com.dnebinger.headless.vitamins.resource.v1_0_0.VitaminResource;

import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Dave Nebinger
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0_0/vitamin.properties",
	scope = ServiceScope.PROTOTYPE, service = VitaminResource.class
)
public class VitaminResourceImpl extends BaseVitaminResourceImpl {
	@Override
	public Page<Vitamin> getViatminsPage(String search, Filter filter, Pagination pagination, Sort[] sorts) throws Exception {
		return super.getViatminsPage(search, filter, pagination, sorts);
	}
}