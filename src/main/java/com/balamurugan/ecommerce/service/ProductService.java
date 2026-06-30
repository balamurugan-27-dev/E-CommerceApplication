package com.balamurugan.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.model.Cart;
import com.balamurugan.ecommerce.model.CartItem;
import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.CartItemRepo;
import com.balamurugan.ecommerce.repository.CartRepo;
import com.balamurugan.ecommerce.repository.ProductsRepo;
import com.balamurugan.ecommerce.repository.UserRepo;

@Service
public class ProductService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	ProductsRepo productsRepo;
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	CartItemRepo cartItemRepo;
	
	public List<Products> getProducts(){
		return productsRepo.findAll();
	}
	

	public Products addProduct(Products product) {
		return productsRepo.save(product);
	}


	public void deleteProduct(int id) {
		
		 productsRepo.deleteById(id);
	}
	
	public void addToCart(int id,int quantity,Authentication authentication) {
		String mail=authentication.getName();
		Users user=userRepo.findByEmail(mail);
		int userId=user.getId();
		Cart cart=cartRepo.findByUserId(userId);
		
		if(cart==null) {
			cart=new Cart();
			cart.setUser(user);
			cart=cartRepo.save(cart);
		}
		
		Products product=productsRepo.findById(id).get();
		
		CartItem cartItem=new CartItem();
		
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		
		cartItemRepo.save(cartItem);
		
		
		
	}
	
	
	
}
