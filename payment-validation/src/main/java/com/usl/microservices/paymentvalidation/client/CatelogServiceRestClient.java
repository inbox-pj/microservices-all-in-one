package com.usl.microservices.paymentvalidation.client;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.paymentvalidation.client.model.ProductCatelogModel;
import com.usl.microservices.paymentvalidation.utils.APIContextHolder;

@Component
public class CatelogServiceRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatelogServiceRestClient.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Optional<ProductCatelogModel> lookupProduct(String productCode) {
		LOGGER.debug(">>> In (payment-validation) CatelogServiceRestClient.lookupProduct: {}. Thread Id: {}", APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
		
		ResponseEntity<ProductCatelogModel> restExchange = restTemplate.exchange("http://zuul-gateway/api/catalog/v1/catelog/product/{productCode}", 
				HttpMethod.GET,
				null,
				ProductCatelogModel.class,
				productCode
				);
		
		if (restExchange.getStatusCode() == HttpStatus.OK) {
			return Optional.ofNullable(restExchange.getBody());
        } else {
            return Optional.empty();
        }
	}

}
