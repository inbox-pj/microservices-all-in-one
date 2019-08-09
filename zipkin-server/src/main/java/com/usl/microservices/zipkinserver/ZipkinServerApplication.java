package com.usl.microservices.zipkinserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import zipkin2.server.internal.EnableZipkinServer;
import zipkin2.server.internal.RegisterZipkinHealthIndicators;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
@RefreshScope
public class ZipkinServerApplication {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZipkinServerApplication.class).listeners(new RegisterZipkinHealthIndicators())
				.run(args);
	}

}
