package com.example.demo.orders.service;

import com.example.demo.barista.entity.BaristaStatusEnum;
import com.example.demo.barista.exception.OrderNotFoundException;
import com.example.demo.orders.entity.OrderEntity;
import com.example.demo.orders.exception.OrderAlreadyPreparingException;
import com.example.demo.orders.exception.OrderCanNotBeEmptyException;
import com.example.demo.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public void addingOrder(OrderEntity order) throws OrderCanNotBeEmptyException {
		if (order != null) {
			orderRepository.save(order);
		} else {
			throw new OrderCanNotBeEmptyException("Order body can not be empty");
		}
	}

	public void cancelOrder(OrderEntity order) throws OrderAlreadyPreparingException {
		if (order != null) {
			if (!order.getOrderStatus().getStatus().equals(BaristaStatusEnum.waiting)) {
				throw new OrderAlreadyPreparingException("Order preparing...");
			}
			orderRepository.delete(order);
		}
	}

	public void deleteFinishedOrder(OrderEntity order) {

		if (order != null) {
			if (order.getOrderStatus().getStatus().equals(BaristaStatusEnum.picked_up)) {
				orderRepository.delete(order);
			}
		}
	}

	public List<OrderEntity> listOfOrders() {
		return orderRepository.findAll();
	}

}
