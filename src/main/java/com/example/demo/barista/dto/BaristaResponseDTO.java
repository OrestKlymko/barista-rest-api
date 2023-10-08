package com.example.demo.barista.dto;


import com.example.demo.barista.entity.BaristaEntity;
import com.example.demo.barista.entity.BaristaStatusEnum;
import com.example.demo.orders.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class BaristaResponseDTO {
	private long orderNumber;
	private BaristaStatusEnum status;

	public static BaristaResponseDTO toModel(BaristaEntity barista){
		return BaristaResponseDTO.builder()
				.orderNumber(barista.getOrder().getOrderNumber())
				.status(barista.getStatus())
				.build();
	}
}
