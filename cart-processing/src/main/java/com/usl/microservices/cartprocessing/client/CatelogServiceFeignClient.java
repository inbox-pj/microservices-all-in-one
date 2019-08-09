package com.usl.microservices.cartprocessing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usl.microservices.cartprocessing.client.model.ProductCatelogModel;

@FeignClient("catalog-service")
public interface CatelogServiceFeignClient {

	@RequestMapping(
			method = RequestMethod.GET, 
			value = "/v1/catelog/product/{productCode}", 
			produces = "application/json")
	ResponseEntity<ProductCatelogModel> lookupProduct(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable("productCode") String productCode);

	@RequestMapping(
			method = RequestMethod.POST, 
			value = "/v1/catelog/reserve/product/{productCode}/qty/{quantity}", 
			produces = "application/json")
	public ResponseEntity<String> reserveProductQuantity(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity);

	@RequestMapping(
			method = RequestMethod.POST, 
			value = "/v1/catelog/consume/product/{productCode}/qty/{quantity}", 
			produces = "application/json")
	public ResponseEntity<String> consumeInventory(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity);

	@RequestMapping(
			method = RequestMethod.POST, 
			value = "/v1/catelog/clear/product/{productCode}/qty/{quantity}", 
			produces = "application/json")
	public ResponseEntity<String> clearReserve(
			@RequestHeader(name = "correlation-id", required = true) String correlationId,
			@PathVariable(name = "productCode", required = true) String productCode,
			@PathVariable(name = "quantity", required = true) Integer quantity);
}
