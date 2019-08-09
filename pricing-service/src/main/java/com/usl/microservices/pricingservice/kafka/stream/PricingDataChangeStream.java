package com.usl.microservices.pricingservice.kafka.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PricingDataChangeStream {

	String OUTPUT = "pricing-change-out";

	@Output(OUTPUT)
	MessageChannel outboundCatalogLookup(); // outbound stream to write to Kafka

}
