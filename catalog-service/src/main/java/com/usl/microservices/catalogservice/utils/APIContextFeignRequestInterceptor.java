package com.usl.microservices.catalogservice.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

// applicable for feign client only
public class APIContextFeignRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();

		if (requestAttributes == null) {
			return;
		}

		HttpServletRequest request = requestAttributes.getRequest();
		if (request == null) {
			return;
		}

		template.header(APIContext.CORRELATION_ID, request.getHeader(APIContext.CORRELATION_ID));
	}

}
