package com.ecommerce.api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	
	@ExceptionHandler(CategoryNotFound.class)
	public ResponseEntity<String>categoryNotFound(CategoryNotFound categoryNotFound)
	{
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categoryNotFound.getMessage());
		
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String>productNotFound(ProductNotFoundException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	
}
