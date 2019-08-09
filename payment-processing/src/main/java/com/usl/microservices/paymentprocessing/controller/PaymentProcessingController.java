package com.usl.microservices.paymentprocessing.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usl.microservices.paymentprocessing.model.PaymentProcessingModel;
import com.usl.microservices.paymentprocessing.services.PaymentProcessingService;

@RestController
@RequestMapping(value = "/v1/processing", produces = "application/json")
public class PaymentProcessingController {

	@Autowired
	private PaymentProcessingService processingService;

	@PostMapping(value = "/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}")
	public ResponseEntity<PaymentProcessingModel> checkout(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "userName", required = true) String userName,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "price", required = true) BigDecimal price,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(processingService.checkout(userName, productCode, price, quantity));
	}
}
