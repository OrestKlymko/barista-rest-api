package com.example.demo.barista.repository;

import com.example.demo.barista.entity.BaristaEntity;
import com.example.demo.orders.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaristaRepository extends JpaRepository<BaristaEntity, Long> {
	public Optional<BaristaEntity> findByOrder(OrderEntity order);
}
