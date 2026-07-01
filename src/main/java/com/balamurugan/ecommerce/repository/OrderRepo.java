package com.balamurugan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
	
	

}
