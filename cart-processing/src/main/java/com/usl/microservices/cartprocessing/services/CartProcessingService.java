package com.usl.microservices.cartprocessing.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.usl.microservices.cartprocessing.client.CatelogServiceFeignClient;
import com.usl.microservices.cartprocessing.client.CatelogServiceRestClient;
import com.usl.microservices.cartprocessing.client.PaymentProcessingFeignClient;
import com.usl.microservices.cartprocessing.client.PaymentProcessingRestClient;
import com.usl.microservices.cartprocessing.client.model.PaymentProcessingModel;
import com.usl.microservices.cartprocessing.client.model.ProductCatelogModel;
import com.usl.microservices.cartprocessing.config.ServiceConfig;
import com.usl.microservices.cartprocessing.model.CartProcessingModel;
import com.usl.microservices.cartprocessing.repository.CartProcessingRepository;
import com.usl.microservices.cartprocessing.utils.APIContextHolder;

@Service
public class CartProcessingService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServiceConfig config;

	@SuppressWarnings("unused")
	@Autowired
	private CatelogServiceFeignClient catelogClient;

	@SuppressWarnings("unused")
	@Autowired
	private PaymentProcessingFeignClient paymentProcessingClient;

	@Autowired
	private PaymentProcessingRestClient paymentProcessingRestClient;

	@Autowired
	private CatelogServiceRestClient catelogRestClient;

	@Autowired
	private CartProcessingRepository repository;

	@HystrixCommand(
			fallbackMethod = "addToCart_fallback", 
			commandKey = "CartProcessingService.addToCart", 
			groupKey = "CartProcessingService", 
			threadPoolKey = "shoppingCartThreadPool", 
			threadPoolProperties = {
				@HystrixProperty(name = "coreSize", value = "30"),
				@HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
						@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "120000"),
						@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
						@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
						@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
						@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
						@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
	public CartProcessingModel addToCart(String userName, String productCode, Integer quantity) {
		// randomlyRunLong(); disabled hystrix failure, enable it for testing purpose

		ProductCatelogModel model = catelogRestClient.lookupProduct(productCode);
		if (model == null) {
			CartProcessingModel cart = new CartProcessingModel();
			cart.setStatus("No Product Found");
			return cart;
		}

		String msg = catelogRestClient.reserveProductQuantity(productCode, quantity);
		if (!"RESERVED".equals(msg)) {
			CartProcessingModel cart = new CartProcessingModel();
			cart.setStatus(msg);
			return cart;
		}

		CartProcessingModel cartModel = new CartProcessingModel();
		cartModel.setUserName(userName);
		cartModel.setProductCode(productCode);
		cartModel.setProductName(model.getProductName());
		cartModel.setQuantity(quantity);
		cartModel.setAmount(model.getPrice().multiply(new BigDecimal(quantity)));
		cartModel.setStatus(msg);
		cartModel.setWhenModified(new Date(System.currentTimeMillis()));
		cartModel.setEnvDescription(config.getMessage());

		return repository.save(cartModel);
	}

	public CartProcessingModel addToCart_fallback(String userName, String productCode, Integer quantity) {
		CartProcessingModel cart = new CartProcessingModel();
		cart.setUserName(userName);
		cart.setProductCode(productCode);
		cart.setQuantity(quantity);
		cart.setEnvDescription(config.getMessage());
		cart.setStatus("Hystrix Fallback response -> No Product Found - " + APIContextHolder.getContext().getCorrelationId());
		return cart;
	}

	@HystrixCommand(
			fallbackMethod = "checkout_fallback", 
			commandKey = "CartProcessingService.checkout", 
			groupKey = "CartProcessingService", 
			threadPoolKey = "shoppingCartThreadPool", 
			threadPoolProperties = {
				@HystrixProperty(name = "coreSize", value = "30"),
				@HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
						@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "45000"),
						@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
						@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
						@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
						@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
						@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
	public CartProcessingModel checkout(Integer shoppingCartId, BigDecimal price) {
		log.debug("CorrelationId in Cart Processing's Checkout :  " + APIContextHolder.getContext().getCorrelationId());

		CartProcessingModel model = repository.findByShoppingCartId(shoppingCartId);
		if (model == null || "PAID".equals(model.getStatus())) {
			return model;
		}

		PaymentProcessingModel processingModel = paymentProcessingRestClient.checkout(model.getUserName(),
				model.getProductCode(), price, model.getQuantity());
		if ("SUCCESS".equals(processingModel.getPaymentStatus())) {
			String msg = catelogRestClient.consumeInventory(model.getProductCode(), model.getQuantity());
			model.setStatus("CONSUEMD".equals(msg) ? "PAID" : msg);
		} else {
			String msg = catelogRestClient.clearReserve(model.getProductCode(), model.getQuantity());
			model.setStatus("CLEARED".equals(msg) ? "FAIL" : msg);
		}

		return repository.save(model);
	}

	@SuppressWarnings("unused")
	private void randomlyRunLong() {
		Random rand = new Random();
		int randomNum = rand.nextInt((3 - 1) + 1) + 1;
		if (randomNum == 3) {
			/*
			 * if (RandomUtils.nextBoolean()) { throw new RuntimeException("Failed!"); }
			 */
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public CartProcessingModel checkout_fallback(Integer shoppingCartId, BigDecimal price) {
		CartProcessingModel cart = new CartProcessingModel();
		cart.setEnvDescription(config.getMessage());
		cart.setStatus("Hystrix Fallback response -> Checkout Failed - " + APIContextHolder.getContext().getCorrelationId());
		return cart;
	}
}
