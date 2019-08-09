package com.usl.microservices.catalogservice.kafka.stream.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.usl.microservices.catalogservice.kafka.stream.CatelogLookupStreams;
import com.usl.microservices.catalogservice.kafka.stream.deliverer.MessageDeliverer;
import com.usl.microservices.catalogservice.kafka.stream.events.impl.CatelogLookupEvent;

@Component
public class CatelogLookupMessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageDeliverer.class);
	
	@StreamListener(CatelogLookupStreams.INPUT_CATELOG_LOOKUP)
	public void handleGreetings(@Payload CatelogLookupEvent event) {
		logger.info("Received Lookup Event: {}", event.getCorrelationId() + " / " + event.getEventBody().getProductCode() + "/" + event.getEventBody().getProductName());
    }
}
