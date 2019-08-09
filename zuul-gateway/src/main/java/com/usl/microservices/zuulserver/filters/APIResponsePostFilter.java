package com.usl.microservices.zuulserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.usl.microservices.zuulserver.utils.APIContext;

import brave.Tracer;

@Component
public class APIResponsePostFilter extends ZuulFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIRequestPreFilter.class);

	@Autowired                                
	private Tracer tracer;
	
	@Autowired
	private APIFilterUtils filterUtils;

	@Override
	public boolean shouldFilter() {
		return APIFilterUtils.SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		LOGGER.debug("Adding the correlation id to the outbound headers. {}", filterUtils.getCorrelationId());

		ctx.getResponse().addHeader(APIContext.CORRELATION_ID, filterUtils.getCorrelationId());
		ctx.getResponse().addHeader(APIContext.TRACE_ID, tracer.currentSpan().toString());
		
		LOGGER.debug("Completing outgoing request for {}.", ctx.getRequest().getRequestURI());

		return null;
	}

	@Override
	public String filterType() {
		return APIFilterUtils.POST_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return APIFilterUtils.FILTER_ORDER;
	}

}
