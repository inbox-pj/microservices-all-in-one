package com.usl.microservices.paymentvalidation.exception.handler;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
	private String corelationId;

	public ExceptionResponse(Date timestamp, String message, String details, String corelationId) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.corelationId = corelationId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public String getCorelationId() {
		return corelationId;
	}

}
