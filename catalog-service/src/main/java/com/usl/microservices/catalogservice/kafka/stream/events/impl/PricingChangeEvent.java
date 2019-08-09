package com.usl.microservices.catalogservice.kafka.stream.events.impl;

import com.usl.microservices.catalogservice.client.model.ProductPricingModel;
import com.usl.microservices.catalogservice.kafka.stream.events.ProcessEvent;

public class PricingChangeEvent  implements ProcessEvent {

	private static final long serialVersionUID = 1L;

	private String correlationId;

	private ProductPricingModel eventBody;
	
	public PricingChangeEvent(String correlationId, ProductPricingModel eventBody) {
		this.correlationId = correlationId;
		this.eventBody = eventBody;
	}

	public String getCorrelationId() {
		return this.correlationId;
	}

	public ProductPricingModel getEventBody() {
		return this.eventBody;
	}
	
}
