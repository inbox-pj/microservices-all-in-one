package com.usl.microservices.cartprocessing.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.cartprocessing.client.model.ProductCatelogModel;
import com.usl.microservices.cartprocessing.utils.APIContextHolder;

@Component
public class CatelogServiceRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatelogServiceRestClient.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public ProductCatelogModel lookupProduct(String productCode) {
		LOGGER.debug(">>> In (payment-validation) CatelogServiceRestClient.lookupProduct: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<ProductCatelogModel> restExchange = restTemplate.exchange("http://zuul-gateway/api/catalog/v1/catelog/product/{productCode}", 
				HttpMethod.GET,
				null,
				ProductCatelogModel.class,
				productCode
				);
		
		return restExchange.getBody();
	}
	
	public String reserveProductQuantity(String productCode, Integer quantity) {
		LOGGER.debug(">>> In (payment-validation) CatelogServiceRestClient.reserveProductQuantity: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<String> restExchange = restTemplate.exchange("http://zuul-gateway/api/catalog/v1/catelog/reserve/product/{productCode}/qty/{quantity}", 
				HttpMethod.POST,
				null,
				String.class,
				productCode,
				quantity
				);
		
		return restExchange.getBody();
	}

	public String consumeInventory(String productCode, Integer quantity) {
		LOGGER.debug(">>> In (payment-validation) CatelogServiceRestClient.consumeInventory: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<String> restExchange = restTemplate.exchange("http://zuul-gateway/api/catalog/v1/catelog/consume/product/{productCode}/qty/{quantity}", 
				HttpMethod.POST,
				null,
				String.class,
				productCode,
				quantity
				);
		
		return restExchange.getBody();
	}

	public String clearReserve(String productCode, Integer quantity) {
		LOGGER.debug(">>> In (payment-validation) CatelogServiceRestClient.clearReserve: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<String> restExchange = restTemplate.exchange("http://zuul-gateway/api/catalog/v1/catelog/clear/product/{productCode}/qty/{quantity}", 
				HttpMethod.POST,
				null,
				String.class,
				productCode,
				quantity
				);
		
		return restExchange.getBody();
	}

}
