package com.usl.microservices.pricingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.usl.microservices.pricingservice.config.ServiceConfig;
import com.usl.microservices.pricingservice.kafka.stream.PricingDataChangeStream;

import brave.sampler.Sampler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCircuitBreaker
@EnableBinding(PricingDataChangeStream.class)
@EnableSwagger2
@RefreshScope
public class PricingServiceApplication {

	@Autowired
	private ServiceConfig serviceConfig;
	
	public static void main(String[] args) {
		SpringApplication.run(PricingServiceApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler() {
	   return Sampler.ALWAYS_SAMPLE;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(serviceConfig.getRedisServerHost(),
				serviceConfig.getRedisServerPort());
		return new JedisConnectionFactory(config);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		 RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		 template.setConnectionFactory(jedisConnectionFactory());
		 
		 return template;
	}
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.usl.microservices.pricingservice"))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Pricing Service API").description("Documentation Pricing Service API v1.0").build());
	}
}
