package com.usl.microservices.catalogservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.usl.microservices.catalogservice.client.model.ProductPricingModel;
import com.usl.microservices.catalogservice.redis.repository.PricingRedisRepository;
import com.usl.microservices.catalogservice.utils.APIContextHolder;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

@Component
public class PricingServiceRestClient {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PricingRedisRepository redisRepo;
	
	@Autowired
	private Tracer tracer;

	private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceRestClient.class);

	public ProductPricingModel checkPrice(String productCode) {

		LOGGER.debug(">>> In (catalog-service) PricingServiceRestClient.checkPrice: {}. Thread Id: {}",
				APIContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());

		ProductPricingModel model = checkRedisCache(productCode);
		if (model != null) {
			LOGGER.debug("Successfully retrieved an Pricing {} from the redis cache: {}", productCode, model);
			return model;
		}

		LOGGER.debug("Unable to locate Pricing from the redis cache: {}.", productCode);

		ResponseEntity<ProductPricingModel> restExchange = restTemplate.exchange(
				"http://zuul-gateway/api/pricing/v1/pricing/product/{productCode}", HttpMethod.GET, null,
				ProductPricingModel.class, productCode);

		model = restExchange.getBody();

		if (model != null) {
			cacheProductPricingModel(model);
		}

		return model;
	}

	private ProductPricingModel checkRedisCache(String productCode) {
		
		Span newSpan = tracer.nextSpan().name("CatalogService.checkRedisCache.ReadPricingDataFromRedis");
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			return redisRepo.findPricingData(productCode);
		} catch (Exception ex) {
			LOGGER.error("Error encountered while trying to retrieve Pricing {} check Redis Cache. Exception {}",
					productCode, ex);
			return null;
		} finally {
			newSpan.tag("peer.service", "redis");
			newSpan.finish();
		}
	}

	private void cacheProductPricingModel(ProductPricingModel model) {
		try {
			redisRepo.savePricingData(model);
		} catch (Exception ex) {
			LOGGER.error("Unable to cache Pricing {} in Redis. Exception {}", model.getProductCode(), ex);
		}
	}

}
