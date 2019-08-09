package com.usl.microservices.paymentprocessing.client;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.paymentprocessing.client.model.PaymentValidationModel;
import com.usl.microservices.paymentprocessing.utils.APIContextHolder;

@Component
public class ValidationServiceRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationServiceRestClient.class);

	@Autowired
	private RestTemplate restTemplate;

	public PaymentValidationModel validatePayment(String userName, String productCode, BigDecimal price,
			Integer quantity) {
		LOGGER.debug(">>> In (payment-processing) ValidationServiceRestClient.validatePayment: {}. Thread Id: {}",
				APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());

		ResponseEntity<PaymentValidationModel> restExchange = restTemplate.exchange(
				"http://zuul-gateway/api/validation/v1/validation/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}",
				HttpMethod.POST, 
				null, 
				PaymentValidationModel.class, 
				userName, 
				productCode, 
				price, 
				quantity);

		return restExchange.getBody();
	}

}
