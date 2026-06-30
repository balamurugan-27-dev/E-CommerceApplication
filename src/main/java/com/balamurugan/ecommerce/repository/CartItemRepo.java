package com.balamurugan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.CartItem;
@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

}
