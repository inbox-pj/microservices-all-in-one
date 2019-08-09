package com.usl.microservices.paymentvalidation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.usl.microservices.paymentvalidation.utils.APIContextHolder;

@Component
public class ServiceConfig {

	@Value("${greeting.message}")
	private String greetingMessage;

	@Value("${redis.server.host}")
	private String redisServerHost;

	@Value("${redis.server.port}")
	private int redisServerPort;

	public String getMessage() {
		return greetingMessage + " " + APIContextHolder.getContext().getCorrelationId();
	}

	public String getRedisServerHost() {
		return redisServerHost;
	}

	public int getRedisServerPort() {
		return redisServerPort;
	}

}
