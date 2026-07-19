package com.balamurugan.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.Exceptions.ResourceNotFoundException;
import com.balamurugan.ecommerce.model.Cart;
import com.balamurugan.ecommerce.model.CartItem;
import com.balamurugan.ecommerce.model.Order;
import com.balamurugan.ecommerce.model.OrderItem;
import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.CartItemRepo;
import com.balamurugan.ecommerce.repository.CartRepo;
import com.balamurugan.ecommerce.repository.OrderItemRepo;
import com.balamurugan.ecommerce.repository.OrderRepo;
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
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderItemRepo orderItemRepo;
	
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
		Users user=userRepo.findByEmail(mail).orElseThrow(()-> new ResourceNotFoundException(mail + "  your mail not register"));
		int userId=user.getId();
		Cart cart=cartRepo.findByUserId(userId).get();	
		
		if(cart==null) {
			cart=new Cart();
			cart.setUser(user);
			cart=cartRepo.save(cart);
		}
		
		Products product=productsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Your Product Id " + id + " Not found"));
		
		CartItem cartItem=new CartItem();
		
		cartItem.setCart(cart);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		
		cartItemRepo.save(cartItem);
		
		
	}
	
	public List<CartItem> getcart(Authentication authentication ){
		
		String mail=authentication.getName();
		Users user= userRepo.findByEmail(mail).get();
		int id=user.getId();
		
		Cart cart=cartRepo.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("Your Cart Not found"));
		
		return  cart.getCartItem();		
	}
	
	public Order order(Authentication authentication) {
		
		String mail=authentication.getName();
		Users user= userRepo.findByEmail(mail).get();
		
		int id=user.getId();
		
		Order order =new Order();
		double total=0;
		
		Cart cart=cartRepo.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("Your Cart Not found"));
		List<OrderItem> orderList=new ArrayList<>();
		
		
		for(CartItem item : cart.getCartItem()) {
			
			OrderItem orderItem=new OrderItem();
			
			orderItem.setProducts(item.getProduct());
			orderItem.setPrice(item.getProduct().getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setOrder(order);
			
			
			
			orderList.add(orderItem);
			
			total+=item.getProduct().getPrice()*item.getQuantity();
		}
		
		order.setOrderItem(orderList);
		order.setTotal(total);
		order.setUser(user);
		
		
		Order final_order=orderRepo.save(order);
		cartItemRepo.deleteAll();
		return final_order;
		
		
		
	}
	
	public List<OrderItem> getOrderItems(Authentication auth){
		
		int  userId=userRepo.findByEmail(auth.getName()).get().getId();
		Order order =orderRepo.findByUserId(userId).get();
		
		return order.getOrderItem();
		
		
		
	}


	public Products GetProductById(int id) {
		return productsRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Your Product ID :" +id +" Not found"));
	}
	
	
	
}
