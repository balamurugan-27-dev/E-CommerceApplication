package com.balamurugan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	
	
}
