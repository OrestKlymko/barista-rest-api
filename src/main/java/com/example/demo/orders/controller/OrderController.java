package com.example.demo.orders.controller;


import com.example.demo.orders.entity.OrderEntity;
import com.example.demo.orders.exception.OrderAlreadyPreparingException;
import com.example.demo.orders.exception.OrderCanNotBeEmptyException;
import com.example.demo.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	@Autowired
	private OrderService orderService;


	@PostMapping("/add")
	public ResponseEntity<?> addOrder(@RequestBody OrderEntity order) {
		try {
			orderService.addingOrder(order);
			return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
		} catch (OrderCanNotBeEmptyException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
		}
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> cancelOrder(@RequestBody OrderEntity order) {
		try {
			orderService.cancelOrder(order);
			return ResponseEntity.ok().body("Order cancelled");
		} catch (OrderAlreadyPreparingException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/all")
	public ResponseEntity<?> getOrdersList() {
		return ResponseEntity.ok(orderService.listOfOrders());
	}
}
