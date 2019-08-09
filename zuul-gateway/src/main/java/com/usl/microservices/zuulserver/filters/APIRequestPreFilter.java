package com.usl.microservices.zuulserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class APIRequestPreFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIRequestPreFilter.class);

	@Autowired
	private APIFilterUtils filterUtils;

	@Override
	public boolean shouldFilter() {
		return APIFilterUtils.SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		if (filterUtils.isCorelationIdExist()) {
			LOGGER.debug("correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
		} else {
			filterUtils.setCorrelationId(filterUtils.generateCorrelationId());
			LOGGER.debug("correlation-id generated in tracking filter: {}. ", filterUtils.getCorrelationId());
		}

		RequestContext ctx = RequestContext.getCurrentContext();
		LOGGER.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());

		return null;
	}

	@Override
	public String filterType() {
		return APIFilterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return APIFilterUtils.FILTER_ORDER;
	}

}
