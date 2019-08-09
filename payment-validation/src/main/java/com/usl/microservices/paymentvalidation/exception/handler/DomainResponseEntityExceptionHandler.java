package com.usl.microservices.paymentvalidation.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usl.microservices.paymentvalidation.exception.ProductNotFoundException;
import com.usl.microservices.paymentvalidation.utils.APIContextHolder;

@ControllerAdvice
@RestController
public class DomainResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false), APIContextHolder.getContext().getCorrelationId());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(ProductNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ProductNotFoundException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false), APIContextHolder.getContext().getCorrelationId());
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	  
}
