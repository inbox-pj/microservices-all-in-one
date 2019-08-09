package com.usl.microservices.catalogservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usl.microservices.catalogservice.client.model.ProductPricingModel;

@FeignClient("pricing-service")
public interface PricingServiceFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/v1/pricing/product/{productCode}", produces = "application/json")
	ResponseEntity<ProductPricingModel> checkPrice(
			@RequestHeader(name = "correlation-id") String correlationId,
			@PathVariable("productCode") String productCode);

}
