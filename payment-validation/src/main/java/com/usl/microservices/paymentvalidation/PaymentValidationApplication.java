package com.usl.microservices.paymentvalidation;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.paymentvalidation.config.ServiceConfig;
import com.usl.microservices.paymentvalidation.utils.APIContextInterceptor;

import brave.sampler.Sampler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableSwagger2
@RefreshScope
public class PaymentValidationApplication {

	@Autowired
	private ServiceConfig serviceConfig;
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentValidationApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		/*
		 * The response body is a stream and if you read it in your interceptor, it
		 * won’t be available for RestTemplate to deserialize it into your object model.
		 * In other words, when you call restTemplate.get… you’ll always get back empty
		 * objects (even as you see the object in your response. Fortunately you can fix
		 * that by using a BufferingClientHttpRequestFactory.
		 */
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

		RestTemplate template = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();

		if (interceptors == null) {
			template.setInterceptors(Collections.singletonList(new APIContextInterceptor()));
		} else {
			interceptors.add(new APIContextInterceptor());
			template.setInterceptors(interceptors);
		}

		return template;
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
				.apis(RequestHandlerSelectors.basePackage("com.usl.microservices.paymentvalidation"))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder().version("1.0").title("Validation Service API").description("Validation Service API v1.0").build());
	}
}
