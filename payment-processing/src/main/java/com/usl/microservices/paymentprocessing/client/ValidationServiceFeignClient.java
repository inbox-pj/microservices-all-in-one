package com.usl.microservices.paymentprocessing.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usl.microservices.paymentprocessing.client.model.PaymentValidationModel;

@FeignClient("payment-validation")
public interface ValidationServiceFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/v1/validation/user/{userName}/product/{productCode}/price/{price}/qty/{quantity}", produces = "application/json")
	ResponseEntity<PaymentValidationModel> validatePayment(@PathVariable("userName") String userName,
			@PathVariable("productCode") String productCode, @PathVariable("price") BigDecimal price,
			@PathVariable("quantity") Integer quantity);

}
