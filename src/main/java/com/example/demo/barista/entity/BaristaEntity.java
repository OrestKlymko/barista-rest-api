package com.example.demo.barista.entity;

import com.example.demo.orders.entity.OrderEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "status_order")
public class BaristaEntity {
	@Id
	@Column(name = "id")
	@JsonIgnore
	private Long statusOrderId;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private BaristaStatusEnum status;

}




