package com.usl.microservices.paymentvalidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usl.microservices.paymentvalidation.model.PaymentValidationModel;

@Repository
public interface PaymentValidationRepository extends JpaRepository<PaymentValidationModel, Integer> {

}
