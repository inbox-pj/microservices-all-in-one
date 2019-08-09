package com.usl.microservices.paymentvalidation.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usl.microservices.paymentvalidation.client.CatelogServiceFeignClient;
import com.usl.microservices.paymentvalidation.client.CatelogServiceRestClient;
import com.usl.microservices.paymentvalidation.client.model.ProductCatelogModel;
import com.usl.microservices.paymentvalidation.config.ServiceConfig;
import com.usl.microservices.paymentvalidation.model.PaymentValidationModel;
import com.usl.microservices.paymentvalidation.repository.PaymentValidationRepository;
import com.usl.microservices.paymentvalidation.utils.APIContextHolder;

@Service
public class PaymentValidationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentValidationService.class);
	
	@Autowired
	private PaymentValidationRepository repository;
	
	@Autowired
    private ServiceConfig config;
	
	@SuppressWarnings("unused")
	@Autowired
	private CatelogServiceFeignClient catelogClient;
	
	@Autowired
	private CatelogServiceRestClient catelogRestClient;
	
	public PaymentValidationModel validatePayment(String userName, String productCode, BigDecimal actualPrice, Integer quantity) {
		
		LOGGER.debug("CorrelationId in Payment Validation's Checkout :  " +  APIContextHolder.getContext().getCorrelationId());
		
		PaymentValidationModel model = new PaymentValidationModel()
				.withEnvDescription(config.getMessage())
				.withProductAmount(actualPrice)
				.withProductCode(productCode)
				.withUserName(userName)
				.withWhenModified(new Date(System.currentTimeMillis()));
		
		Optional<ProductCatelogModel> catelogModelEntity = catelogRestClient.lookupProduct(productCode);
		if(!catelogModelEntity.isPresent()) {
			model = model
					.withStatus("FAILED, Product not found");
		} else {
			ProductCatelogModel catelogModel = catelogModelEntity.get();
			if(catelogModel.getReservQuanitity() < quantity) {
				model = model
						.withStatus("FAILED, Stock Over.");
			} else {
				BigDecimal expectedPrice =  catelogModel.getPrice().multiply(new BigDecimal(quantity));
				if(actualPrice.compareTo(expectedPrice) == -1) {
					model = model
							.withStatus("FAILED, Insuffient amount paid.");
				} else {
					model = model
							.withStatus("SUCCESS");
				}
			}
		}
		
		repository.save(model);
		return model;
	}
	
}
