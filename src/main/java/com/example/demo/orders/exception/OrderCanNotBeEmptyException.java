package com.example.demo.orders.exception;

public class OrderCanNotBeEmptyException extends Throwable {
	public OrderCanNotBeEmptyException(String message) {
		super(message);
	}
}
