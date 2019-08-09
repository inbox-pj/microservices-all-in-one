package com.usl.microservices.cartprocessing.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usl.microservices.cartprocessing.model.CartProcessingModel;
import com.usl.microservices.cartprocessing.services.CartProcessingService;

@RestController
@RequestMapping(value = "/v1/cart", produces = "application/json")
public class CartProcessingController {

	@Autowired
	private CartProcessingService cartProcessingService;

	@PostMapping(value = "/add/user/{userName}/product/{productCode}/qty/{quantity}")
	public ResponseEntity<CartProcessingModel> addToCart(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "userName", required = true) String userName,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(cartProcessingService.addToCart(userName, productCode, quantity));
	}

	@PostMapping(value = "/checkout/tracking/{traceId}/price/{price}")
	public ResponseEntity<CartProcessingModel> checkout(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "traceId", required = true) Integer traceId,
			@PathVariable(name = "price", required = true) BigDecimal price) {
		return ResponseEntity.ok(cartProcessingService.checkout(traceId, price));
	}

}
