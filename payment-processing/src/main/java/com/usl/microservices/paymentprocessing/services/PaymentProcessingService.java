package com.usl.microservices.paymentprocessing.services;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usl.microservices.paymentprocessing.client.ValidationServiceFeignClient;
import com.usl.microservices.paymentprocessing.client.ValidationServiceRestClient;
import com.usl.microservices.paymentprocessing.client.model.PaymentValidationModel;
import com.usl.microservices.paymentprocessing.config.ServiceConfig;
import com.usl.microservices.paymentprocessing.model.PaymentProcessingModel;
import com.usl.microservices.paymentprocessing.repository.PaymentProcessingRepository;
import com.usl.microservices.paymentprocessing.utils.APIContextHolder;

@Service
public class PaymentProcessingService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PaymentProcessingRepository repository;

	@Autowired
	private ServiceConfig config;

	@SuppressWarnings("unused")
	@Autowired
	private ValidationServiceFeignClient validationClient;

	@Autowired
	private ValidationServiceRestClient validationRestClient;

	public PaymentProcessingModel checkout(String userName, String productCode, BigDecimal price, Integer quantity) {

		log.debug("CorrelationId in Payment Processing's Checkout :  " + APIContextHolder.getContext().getCorrelationId());

		// Step#1 validate payments
		PaymentProcessingModel model = new PaymentProcessingModel();
		model.setEnvDescription(config.getMessage());
		model.setPaidAmount(price);
		model.setPaidDate(new Date(System.currentTimeMillis()));
		model.setProductCode(productCode);
		model.setUserName(userName);
		model.setWhenModified(new Date(System.currentTimeMillis()));
		model.setQuantity(quantity);

		PaymentValidationModel validationModel = validationRestClient.validatePayment(userName, productCode, price, quantity);
		if ("SUCCESS".equals(validationModel.getStatus())) {
			model.setPaymentStatus(validationModel.getStatus());
		} else {
			model.setPaymentStatus(validationModel.getStatus());
		}

		return repository.save(model);
	}

}
