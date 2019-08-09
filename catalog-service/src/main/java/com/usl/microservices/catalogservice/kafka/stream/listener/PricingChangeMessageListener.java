package com.usl.microservices.catalogservice.kafka.stream.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.usl.microservices.catalogservice.kafka.stream.CatelogLookupStreams;
import com.usl.microservices.catalogservice.kafka.stream.deliverer.MessageDeliverer;
import com.usl.microservices.catalogservice.kafka.stream.events.impl.PricingChangeEvent;
import com.usl.microservices.catalogservice.redis.repository.PricingRedisRepository;

@Component
public class PricingChangeMessageListener {

	private static final Logger logger = LoggerFactory.getLogger(MessageDeliverer.class);

	@Autowired
	private PricingRedisRepository redisRepo;

	@StreamListener(CatelogLookupStreams.INPUT_PRICE_CHANGE)
	public void handleGreetings(@Payload PricingChangeEvent event) {
		logger.info("Received Pricing data change Event: {}", event.getCorrelationId() + " / "
				+ event.getEventBody().getProductCode() + "/" + event.getEventBody().getProductCode());
		
		redisRepo.updatePricingData(event.getEventBody());
	}
}
