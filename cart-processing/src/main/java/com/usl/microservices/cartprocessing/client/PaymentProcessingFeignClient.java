package com.usl.microservices.cartprocessing.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usl.microservices.cartprocessing.client.model.PaymentProcessingModel;

@FeignClient("payment-processing")
public interface PaymentProcessingFeignClient {

	@RequestMapping(
			method = RequestMethod.POST, 
			value = "/v1/processing/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}", 
			produces = "application/json")
	public ResponseEntity<PaymentProcessingModel> checkout(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "userName", required = true) String userName,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "price", required = true) BigDecimal price,
			@PathVariable(name = "quantity", required = true) Integer quantity);
}
