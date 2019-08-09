package com.usl.microservices.zuulserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class APIRouteFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(APIRequestPreFilter.class);

	@Override
	public boolean shouldFilter() {
		return APIFilterUtils.SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		LOGGER.debug("Inside APIRouteFilter.run()");
		return null;
	}

	@Override
	public String filterType() {
		return APIFilterUtils.ROUTE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return APIFilterUtils.FILTER_ORDER;
	}

}
