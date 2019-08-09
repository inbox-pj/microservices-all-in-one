package com.usl.microservices.pricingservice.kafka.stream.events.impl;

import com.usl.microservices.pricingservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.pricingservice.model.ProductPricingModel;

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
