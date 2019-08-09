package com.usl.microservices.catalogservice.kafka.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface CatelogLookupStreams {

	String INPUT_CATELOG_LOOKUP = "catalog-lookup-in";
	String INPUT_PRICE_CHANGE = "pricing-change-in";
	
	String OUTPUT = "catalog-lookup-out";

	
	
	@Input(INPUT_CATELOG_LOOKUP)
	SubscribableChannel inboundCatalogLookup();	// inbound stream to read from Kafka
	
	@Input(INPUT_PRICE_CHANGE)
	SubscribableChannel inboundPricingLookup();	// inbound stream to read from Kafka

	@Output(OUTPUT)
	MessageChannel outboundCatalogLookup();	// outbound stream to write to Kafka

}
