package com.example.demo.barista.controller;


import com.example.demo.barista.exception.OrderNotFoundException;
import com.example.demo.barista.service.BaristaService;
import com.example.demo.orders.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/barista")
public class BaristaController {

	@Autowired
	private BaristaService baristaService;

	@PostMapping("/change-status")
	public ResponseEntity<?> processOrder(@RequestBody OrderEntity order) {
		try {
			baristaService.processOrder(order);
			return ResponseEntity.status(HttpStatus.OK).body("Order change status");
		} catch (OrderNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/{id}/notification")
	public ResponseEntity<?> getNotificationOfFinishedOrder(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body("Order with id "+id+" already picked up");
	}

	@GetMapping("/all-orders")
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.status(HttpStatus.OK).body(baristaService.getAllOrders());
	}
}
