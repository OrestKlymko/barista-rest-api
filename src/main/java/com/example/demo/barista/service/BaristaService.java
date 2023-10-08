package com.example.demo.barista.service;


import com.example.demo.barista.dto.BaristaResponseDTO;
import com.example.demo.barista.entity.BaristaEntity;
import com.example.demo.barista.entity.BaristaStatusEnum;
import com.example.demo.barista.exception.OrderNotFoundException;
import com.example.demo.barista.repository.BaristaRepository;
import com.example.demo.orders.entity.OrderEntity;
import com.example.demo.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.LinkedList;
import java.util.List;


@Service
public class BaristaService {
	@Autowired
	private BaristaRepository baristaRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private RestTemplate restTemplate;


	public void processOrder(OrderEntity order) throws OrderNotFoundException {
		if (order==null){
			throw new OrderNotFoundException("Order can not be empty");
		}

		BaristaEntity baristaEntity = baristaRepository.findByOrder(order)
				.orElseThrow(() -> new OrderNotFoundException("Order with order number: " + order.getOrderNumber() + " not found"));
		BaristaEntity orderStatus = baristaEntity.getOrder().getOrderStatus();


		switch (orderStatus.getStatus()) {
			case waiting:
				orderStatus.setStatus(BaristaStatusEnum.preparation);
				baristaRepository.save(baristaEntity);
				break;
			case preparation:
				orderStatus.setStatus(BaristaStatusEnum.finished);
				baristaRepository.save(baristaEntity);
				break;
			case finished:
				orderStatus.setStatus(BaristaStatusEnum.picked_up);
				baristaRepository.save(baristaEntity);
				break;
			case picked_up:
				sendNotificationToFinishOrder(order);
				orderService.deleteFinishedOrder(orderStatus.getOrder());
				break;
		}
	}

	private void sendNotificationToFinishOrder(OrderEntity order) {
		String baseUrl =
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		String url = String.format("%s/api/v1/barista/%s/notification",baseUrl,order.getOrderNumber());
		restTemplate.getForObject(url,String.class);
	}

	public List<BaristaResponseDTO> getAllOrders() {
		List<BaristaEntity> all = baristaRepository.findAll();
		List<BaristaResponseDTO> baristaResponseDTOList = new LinkedList<>();
		for (BaristaEntity baristaEntity : all) {
			baristaResponseDTOList.add(BaristaResponseDTO.toModel(baristaEntity));
		}
		return baristaResponseDTOList;
	}
}
