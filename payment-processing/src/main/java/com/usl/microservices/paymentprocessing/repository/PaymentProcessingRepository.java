package com.usl.microservices.paymentprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.paymentprocessing.model.PaymentProcessingModel;

@Repository
public interface PaymentProcessingRepository extends JpaRepository<PaymentProcessingModel, Integer> {

}
