package com.usl.microservices.paymentvalidation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usl.microservices.paymentvalidation.client.model.ProductCatelogModel;

@FeignClient("catalog-service")
public interface CatelogServiceFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/v1/catelog/product/{productCode}", produces = "application/json")
	ResponseEntity<ProductCatelogModel> lookupProduct(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable("productCode") String productCode);

}
