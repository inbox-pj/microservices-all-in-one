package com.usl.microservices.cartprocessing.client;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.cartprocessing.client.model.PaymentProcessingModel;
import com.usl.microservices.cartprocessing.utils.APIContextHolder;

@Component
public class PaymentProcessingRestClient {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessingRestClient.class);
	
	public PaymentProcessingModel checkout(String userName, String productCode, BigDecimal price, Integer quantity) {
		LOGGER.debug(">>> In (cart-processing) PaymentProcessingRestClient.checkout: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		
		ResponseEntity<PaymentProcessingModel> restExchange = restTemplate.exchange("http://zuul-gateway/api/payment/v1/processing/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}", 
				HttpMethod.POST,
				null,
				PaymentProcessingModel.class,
				userName,
				productCode,
				price, 
				quantity
				);
		
		return restExchange.getBody();
				
	}
}
