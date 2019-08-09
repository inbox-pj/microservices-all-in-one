package com.usl.microservices.catalogservice.kafka.stream.deliverer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.usl.microservices.catalogservice.kafka.stream.CatelogLookupStreams;
import com.usl.microservices.catalogservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.catalogservice.utils.APIContextHolder;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;

@Component
public class MessageDeliverer<T extends ProcessEvent> {

	private static final Logger logger = LoggerFactory.getLogger(MessageDeliverer.class);

	private CatelogLookupStreams stream;
	
	@Autowired
	private Tracer tracer;

	@Autowired
	public MessageDeliverer(CatelogLookupStreams stream) {
		this.stream = stream;
	}

	public void pubhishEvent(T eventBody) {
		logger.debug("Sending Kafka message for Catelog Service Id: {}",
				APIContextHolder.getContext().getCorrelationId());

		Span newSpan = tracer.nextSpan().name("CatelogService.MessageDeliverer");
		try(SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			stream.outboundCatalogLookup().send(MessageBuilder
					.withPayload(eventBody)
					.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
					.build());
		} finally {
			newSpan.tag("peer.service", "catelog.kafka.catalog-lookup-out");
			newSpan.finish();
		}
	}
}
