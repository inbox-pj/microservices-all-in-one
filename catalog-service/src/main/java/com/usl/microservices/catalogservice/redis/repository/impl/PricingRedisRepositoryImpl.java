package com.usl.microservices.catalogservice.redis.repository.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.usl.microservices.catalogservice.client.model.ProductPricingModel;
import com.usl.microservices.catalogservice.redis.repository.PricingRedisRepository;

@Repository
public class PricingRedisRepositoryImpl implements PricingRedisRepository {

	private static final String HASH_NAME = "PRICING_DATA";

	private RedisTemplate<String, ProductPricingModel> redisTemplate;

	private HashOperations<String, String, ProductPricingModel> hashOperations;

	public PricingRedisRepositoryImpl() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Autowired
	private PricingRedisRepositoryImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void savePricingData(ProductPricingModel org) {
		hashOperations.put(HASH_NAME, org.getProductCode(), org);

	}

	@Override
	public void updatePricingData(ProductPricingModel org) {
		hashOperations.put(HASH_NAME, org.getProductCode(), org);

	}

	@Override
	public void deletePricingData(String productCode) {
		hashOperations.delete(HASH_NAME, productCode);

	}

	@Override
	public ProductPricingModel findPricingData(String productCode) {
		return (ProductPricingModel) hashOperations.get(HASH_NAME, productCode);
	}

}
