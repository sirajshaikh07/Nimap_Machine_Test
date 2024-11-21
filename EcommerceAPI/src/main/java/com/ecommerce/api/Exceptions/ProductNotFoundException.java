package com.ecommerce.api.Exceptions;

public class ProductNotFoundException extends RuntimeException {

	
	public ProductNotFoundException(String exception)
	{
		super(exception);
	}
}
