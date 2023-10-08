package com.example.demo.orders.exception;

public class OrderAlreadyPreparingException extends Throwable {
	public OrderAlreadyPreparingException(String message) {
		super(message);
	}
}
