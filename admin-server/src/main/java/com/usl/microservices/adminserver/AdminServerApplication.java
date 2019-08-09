package com.usl.microservices.adminserver;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAdminServer
@RefreshScope
public class AdminServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminServerApplication.class)
        .web(WebApplicationType.REACTIVE)
        .run(args);
		
		//SpringApplication.run(AdminServerApplication.class, args);
	}

}
