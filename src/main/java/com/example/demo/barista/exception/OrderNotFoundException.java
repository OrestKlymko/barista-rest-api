package com.example.demo.barista.exception;

public class OrderNotFoundException extends Throwable {
	public OrderNotFoundException(String message) {
		super(message);
	}
}
