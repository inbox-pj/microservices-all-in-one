package com.usl.microservices.pricingservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usl.microservices.pricingservice.model.ProductPricingModel;
import com.usl.microservices.pricingservice.services.PricingService;

@RestController
@RequestMapping(value = "/v1/pricing", produces = "application/json")
public class PricingServiceController {

	@Autowired
	private PricingService pricingService;

	@GetMapping(value = "/product/{productCode}")
	public ResponseEntity<ProductPricingModel> checkPrice(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "productCode", required = true) String productCode) {
		return ResponseEntity.ok(pricingService.rerievePriceInfo(productCode));
	}

	@PostMapping(value = "/product/{productCode}/price/{price}")
	public ResponseEntity<ProductPricingModel> updatePrice(
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "price", required = true) BigDecimal price) {
		return ResponseEntity.ok(pricingService.updatePrice(productCode, price));
	}
}
