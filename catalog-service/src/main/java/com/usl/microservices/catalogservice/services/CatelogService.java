package com.usl.microservices.catalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usl.microservices.catalogservice.client.PricingServiceFeignClient;
import com.usl.microservices.catalogservice.client.PricingServiceRestClient;
import com.usl.microservices.catalogservice.client.model.ProductPricingModel;
import com.usl.microservices.catalogservice.config.ServiceConfig;
import com.usl.microservices.catalogservice.kafka.stream.deliverer.MessageDeliverer;
import com.usl.microservices.catalogservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.catalogservice.kafka.stream.events.impl.CatelogLookupEvent;
import com.usl.microservices.catalogservice.model.AgreegateCatelogModel;
import com.usl.microservices.catalogservice.model.ProductCatelogModel;
import com.usl.microservices.catalogservice.repository.CatelogRepository;
import com.usl.microservices.catalogservice.utils.APIContextHolder;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

@Service
public class CatelogService {

	@Autowired
	private CatelogRepository repository;

	@SuppressWarnings("unused")
	@Autowired
	private PricingServiceFeignClient pricingClient;

	@Autowired
	private PricingServiceRestClient pricingRestTemplateClient;

	@Autowired 
	private MessageDeliverer<ProcessEvent> deliverer;
	
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ServiceConfig config;

	public ProductCatelogModel lookupProduct(String productCode) {
		ProductCatelogModel model = findByProductCode(productCode);
		if (model != null) {
			ProductPricingModel pricingModel = pricingRestTemplateClient.checkPrice(productCode);
			if (pricingModel != null) {
				model.setPrice(pricingModel.getPrice());
			}

			model.setEnvDescription(config.getMessage());
		}

		return model;
	}

	public AgreegateCatelogModel searchInventory() {
		Iterable<ProductCatelogModel> iterable = repository.findAll();

		AgreegateCatelogModel agreegateModel = new AgreegateCatelogModel();
		for (ProductCatelogModel productCatelogModel : iterable) {
			productCatelogModel.setEnvDescription(config.getMessage());

			ProductPricingModel pricingModel = pricingRestTemplateClient
					.checkPrice(productCatelogModel.getProductCode());
			if (pricingModel != null) {
				productCatelogModel.setPrice(pricingModel.getPrice());
			}

			agreegateModel.addProductCatelog(productCatelogModel);
			
			deliverer.pubhishEvent(new CatelogLookupEvent(APIContextHolder.getContext().getCorrelationId(), productCatelogModel));
		}

		return agreegateModel;
	}

	public String reserveProductQuantity(String productCode, Integer quantity) {
		ProductCatelogModel model = lookupProduct(productCode);

		if (model != null) {
			if (model.getQuanitity() >= quantity) {
				model.setQuanitity(model.getQuanitity() - quantity);
				model.setReservQuanitity(model.getReservQuanitity() + quantity);
				repository.save(model);
				return "RESERVED";
			} else {
				return "INSUFFICIENT_QTY";
			}
		}
		return "PRODUCT_NOT_FOUND";
	}

	public String consumeInventory(String productCode, Integer quantity) {
		ProductCatelogModel model = findByProductCode(productCode);

		if (model != null) {
			if (model.getReservQuanitity() >= quantity) {
				model.setReservQuanitity(model.getReservQuanitity() - quantity);
				repository.save(model);
				return "CONSUEMD";
			} else {
				return "INSUFFICIENT_QTY";
			}
		}
		return "PRODUCT_NOT_FOUND";
	}

	public String clearReserve(String productCode, Integer quantity) {
		ProductCatelogModel model = findByProductCode(productCode);

		if (model != null) {
			if (model.getReservQuanitity() >= quantity) {
				model.setReservQuanitity(model.getReservQuanitity() - quantity);
				model.setQuanitity(model.getQuanitity() + quantity);
				repository.save(model);
				return "CLEARED";
			} else {
				return "INSUFFICIENT_QTY";
			}
		}
		return "PRODUCT_NOT_FOUND";
	}

	public String updateInventory(String productCode, Integer quantity) {
		ProductCatelogModel model = findByProductCode(productCode);

		if (model != null) {
			model.setQuanitity(model.getQuanitity() + quantity);
			repository.save(model);
			return "SUCCESS";
		}
		return "PRODUCT_NOT_FOUND";
	}

	private ProductCatelogModel findByProductCode(String productCode) {
		Span newSpan = tracer.nextSpan().name("PricingService.findByProductCode");
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			return repository.findByProductCode(productCode);
		} finally {
			newSpan.tag("peer.service", "mysql");
			newSpan.finish();
		}
	}
}
