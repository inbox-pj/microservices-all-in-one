package com.usl.microservices.pricingservice.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class APIContextInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIContextInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest arg0, byte[] arg1, ClientHttpRequestExecution arg2)
			throws IOException {
		logRequest(arg0, arg1);
		HttpHeaders headers = arg0.getHeaders();
		headers.add(APIContext.CORRELATION_ID, APIContextHolder.getContext().getCorrelationId());

		ClientHttpResponse response = arg2.execute(arg0, arg1);
		logResponse(response);
		return response;

	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("===========================request begin================================================");
			LOGGER.debug("URI         : {}", request.getURI());
			LOGGER.debug("Method      : {}", request.getMethod());
			LOGGER.debug("Headers     : {}", request.getHeaders());
			LOGGER.debug("Request body: {}", new String(body, "UTF-8"));
			LOGGER.debug("==========================request end================================================");
		}
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("============================response begin==========================================");
			LOGGER.debug("Status code  : {}", response.getStatusCode());
			LOGGER.debug("Status text  : {}", response.getStatusText());
			LOGGER.debug("Headers      : {}", response.getHeaders());
			LOGGER.debug("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
			LOGGER.debug("=======================response end=================================================");
		}
	}
}
