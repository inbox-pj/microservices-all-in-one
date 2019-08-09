package com.usl.microservices.pricingservice.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usl.microservices.pricingservice.config.ServiceConfig;
import com.usl.microservices.pricingservice.exception.ProductNotFoundException;
import com.usl.microservices.pricingservice.kafka.stream.deliverer.MessageDeliverer;
import com.usl.microservices.pricingservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.pricingservice.kafka.stream.events.impl.PricingChangeEvent;
import com.usl.microservices.pricingservice.model.ProductPricingModel;
import com.usl.microservices.pricingservice.repository.PricingRepository;
import com.usl.microservices.pricingservice.utils.APIContextHolder;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

@Service
public class PricingService {

	@Autowired
	private PricingRepository repository;

	@Autowired 
	private MessageDeliverer<ProcessEvent> deliverer;
	
	@Autowired
	private ServiceConfig config;
	
	@Autowired
	private Tracer tracer;

	public ProductPricingModel rerievePriceInfo(String productCode) {
		Span newSpan = tracer.nextSpan().name("PricingService.rerievePriceInfo");
		
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			ProductPricingModel model = repository.findByProductCode(productCode);
			if (model == null) {
				throw new ProductNotFoundException("No Product found for " + productCode);
			}

			model.setEnvDescription(config.getMessage());
			return model;
		} finally {
			newSpan.tag("peer.service", "mysql");
			newSpan.finish();
		}
	}

	public ProductPricingModel updatePrice(String productCode, BigDecimal price) {
		ProductPricingModel model = rerievePriceInfo(productCode);
		model.setPrice(price);
		model =  repository.save(model);
		
		deliverer.pubhishEvent(new PricingChangeEvent(APIContextHolder.getContext().getCorrelationId(), model));
		
		return model;
	}
}
