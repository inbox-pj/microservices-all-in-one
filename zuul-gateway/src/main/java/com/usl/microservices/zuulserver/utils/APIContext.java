package com.usl.microservices.zuulserver.utils;

import org.springframework.stereotype.Component;

@Component
public class APIContext {
	public static final String CORRELATION_ID = "correlation-id";

	public static final String TRACE_ID = "trace-id";
	
	private String correlationId;

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
