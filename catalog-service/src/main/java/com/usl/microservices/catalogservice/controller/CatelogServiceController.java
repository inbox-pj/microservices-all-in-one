package com.usl.microservices.catalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usl.microservices.catalogservice.model.AgreegateCatelogModel;
import com.usl.microservices.catalogservice.model.ProductCatelogModel;
import com.usl.microservices.catalogservice.services.CatelogService;

@RestController
@RequestMapping(value = "/v1/catelog", produces = "application/json")
public class CatelogServiceController {

	@Autowired
	private CatelogService catelogService;

	@GetMapping(value = "/product/{productCode}")
	public ResponseEntity<ProductCatelogModel> lookupProduct(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "productCode", required = true) String productCode) {
		return ResponseEntity.ok(catelogService.lookupProduct(productCode));
	}

	@GetMapping(value = "/search/all")
	public ResponseEntity<AgreegateCatelogModel> searchInventory() {
		return ResponseEntity.ok(catelogService.searchInventory());
	}

	@PostMapping(value = "/reserve/product/{productCode}/qty/{quantity}")
	public ResponseEntity<String> reserveProductQuantity(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(catelogService.reserveProductQuantity(productCode, quantity));
	}

	@PostMapping(value = "/consume/product/{productCode}/qty/{quantity}")
	public ResponseEntity<String> consumeInventory(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(catelogService.consumeInventory(productCode, quantity));
	}

	@PostMapping(value = "/clear/product/{productCode}/qty/{quantity}")
	public ResponseEntity<String> clearReserve(
			@RequestHeader(name = "correlation-id", required = true) String correlationId, 
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(catelogService.clearReserve(productCode, quantity));
	}

	@PostMapping(value = "/update/product/{productCode}/qty/{quantity}")
	public ResponseEntity<String> updateInventory(
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity) {
		return ResponseEntity.ok(catelogService.updateInventory(productCode, quantity));
	}

}
