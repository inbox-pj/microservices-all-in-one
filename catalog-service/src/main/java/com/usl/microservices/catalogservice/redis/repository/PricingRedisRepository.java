package com.usl.microservices.catalogservice.redis.repository;

import com.usl.microservices.catalogservice.client.model.ProductPricingModel;

public interface PricingRedisRepository {

	void savePricingData(ProductPricingModel org);

	void updatePricingData(ProductPricingModel org);

	void deletePricingData(String productCode);

	ProductPricingModel findPricingData(String productCode);
}
