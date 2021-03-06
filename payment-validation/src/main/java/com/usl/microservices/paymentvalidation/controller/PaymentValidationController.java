package com.usl.microservices.paymentvalidation.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usl.microservices.paymentvalidation.model.PaymentValidationModel;
import com.usl.microservices.paymentvalidation.services.PaymentValidationService;

@RestController
@RequestMapping(value = "/v1/validation", produces = "application/json")
public class PaymentValidationController {

	@Autowired
	private PaymentValidationService validationService;

	@PostMapping(value = "/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}")
	public ResponseEntity<PaymentValidationModel> validatePayment(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "userName", required = true) String userName,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "price", required = true) BigDecimal price,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(validationService.validatePayment(userName, productCode, price, quantity));
	}
}
