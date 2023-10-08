package com.example.demo.orders.entity;


import com.example.demo.barista.entity.BaristaEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "orders")
@Entity

public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_number")
	@Getter
	private Long orderNumber;
	@Column(name = "type")
	@Getter
	@Setter
	private String coffeeType;
	@Column(name = "size")
	@Getter
	@Setter
	private String size;
	@Column(name = "milk")
	@Getter
	@Setter
	private String milk;
	@Column(name = "delivery")
	@Getter
	@Setter
	private String delivery;
	@Column(name = "price")
	@Getter
	@Setter
	private BigDecimal price;
	@JsonManagedReference
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	@Getter
	@Setter
	private BaristaEntity orderStatus;
}


