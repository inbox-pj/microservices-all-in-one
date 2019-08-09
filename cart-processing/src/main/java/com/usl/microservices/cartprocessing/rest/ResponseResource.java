package com.usl.microservices.cartprocessing.rest;

public class ResponseResource<T> {

	private final T resource;
	private final boolean hasError;

	private ResponseResource(T resource, boolean hasError) {
		this.resource = resource;
		this.hasError = hasError;
	}
	
	public T getResource() {
		return this.resource;
	}
	
	public boolean isError() {
		return this.hasError;
	}
	
	public static <T> ResponseResource<T> create(T resource) {
		return new ResponseResource<T>(resource, false);
	}
	
	public static <T> ResponseResource<T> error(T resource) {
		return new ResponseResource<T>(resource, true);
	}
}
