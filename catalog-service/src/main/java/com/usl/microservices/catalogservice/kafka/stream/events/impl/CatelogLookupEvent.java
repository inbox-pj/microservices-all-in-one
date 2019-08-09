package com.usl.microservices.catalogservice.kafka.stream.events.impl;

import com.usl.microservices.catalogservice.kafka.stream.events.ProcessEvent;
import com.usl.microservices.catalogservice.model.ProductCatelogModel;

public class CatelogLookupEvent implements ProcessEvent {

	private static final long serialVersionUID = 1L;

	private String correlationId;

	private ProductCatelogModel eventBody;
	
	public CatelogLookupEvent(String correlationId, ProductCatelogModel eventBody) {
		this.correlationId = correlationId;
		this.eventBody = eventBody;
	}

	public String getCorrelationId() {
		return this.correlationId;
	}

	public ProductCatelogModel getEventBody() {
		return this.eventBody;
	}
	
	

}
