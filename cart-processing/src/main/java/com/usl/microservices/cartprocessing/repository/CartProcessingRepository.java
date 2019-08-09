package com.usl.microservices.cartprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.cartprocessing.model.CartProcessingModel;

@Repository
public interface CartProcessingRepository extends JpaRepository<CartProcessingModel, Integer> {

	public CartProcessingModel findByShoppingCartId(Integer shoppingCartId);
}
