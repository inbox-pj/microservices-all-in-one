package com.usl.microservices.pricingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.pricingservice.model.ProductPricingModel;

@Repository
public interface PricingRepository extends JpaRepository<ProductPricingModel, Integer> {

	public ProductPricingModel findByProductCode(String productCode);
}
