package com.usl.microservices.paymentprocessing.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class APIContextFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIContextFilter.class);

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;

		APIContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(APIContext.CORRELATION_ID));

		LOGGER.debug("Payment Processing Service Incoming Correlation id: {}", APIContextHolder.getContext().getCorrelationId());

		arg2.doFilter(httpServletRequest, arg1);
	}

}
