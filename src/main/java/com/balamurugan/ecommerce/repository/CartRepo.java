package com.balamurugan.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.Cart;
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	Optional<Cart> findByUserId(int id);
}
