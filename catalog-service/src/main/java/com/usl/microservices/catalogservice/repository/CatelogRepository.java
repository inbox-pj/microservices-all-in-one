package com.usl.microservices.catalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.catalogservice.model.ProductCatelogModel;

@Repository
public interface CatelogRepository extends JpaRepository<ProductCatelogModel, Integer> {

	public ProductCatelogModel findByProductCode(String productCode);

}
