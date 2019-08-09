package com.usl.microservices.pricingservice.kafka.stream.deliverer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.usl.microservices.pricingservice.kafka.stream.PricingDataChangeStream;
import com.usl.microservices.pricingservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.pricingservice.utils.APIContextHolder;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

@Component
public class MessageDeliverer<T extends ProcessEvent> {

	private static final Logger logger = LoggerFactory.getLogger(MessageDeliverer.class);

	private PricingDataChangeStream stream;

	@Autowired
	private Tracer tracer;
	
	@Autowired
	public MessageDeliverer(PricingDataChangeStream stream) {
		this.stream = stream;
	}

	public void pubhishEvent(T eventBody) {
		logger.debug("Sending Kafka message for Pricing Service change data: {}",
				APIContextHolder.getContext().getCorrelationId());

		Span newSpan = tracer.nextSpan().name("PricingService.MessageDeliverer");
		
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			stream.outboundCatalogLookup().send(MessageBuilder
					.withPayload(eventBody)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
					.build());
		} finally {
			newSpan.tag("peer.service", "pricing.kafka.pricing-change-out");
			newSpan.finish();
		}
		
	}
}
