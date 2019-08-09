package com.usl.microservices.zuulserver.filters;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;
import com.usl.microservices.zuulserver.utils.APIContext;

@Component
public class APIFilterUtils {

	public static final String PRE_FILTER_TYPE = "pre";
	public static final String POST_FILTER_TYPE = "post";
	public static final String ROUTE_FILTER_TYPE = "route";

	public static final boolean SHOULD_FILTER = true;

	public static final int FILTER_ORDER = 1;

	public String getCorrelationId() {
		RequestContext ctx = RequestContext.getCurrentContext();

		if (ctx.getRequest().getHeader(APIContext.CORRELATION_ID) != null) {
			return ctx.getRequest().getHeader(APIContext.CORRELATION_ID);
		} else {
			return ctx.getZuulRequestHeaders().get(APIContext.CORRELATION_ID);
		}
	}

	public void setCorrelationId(String correlationId) {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(APIContext.CORRELATION_ID, correlationId);
	}

	public boolean isCorelationIdExist() {
		if (StringUtils.isEmpty(getCorrelationId())) {
			return false;
		}

		return true;
	}

	public String generateCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
