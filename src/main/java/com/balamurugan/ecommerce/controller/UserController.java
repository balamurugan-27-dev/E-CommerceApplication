package com.balamurugan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.model.CartItem;
import com.balamurugan.ecommerce.model.Order;
import com.balamurugan.ecommerce.model.OrderItem;
import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.service.ProductService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/getproducts") // to get all products
	public List<Products> get(){
		return productService.getProducts();
	}
	
	@GetMapping("/getproductbyid") //  get product by id
	public ResponseEntity<?> getProductById(@RequestParam int id){
		Products products=productService.GetProductById(id);
		return ResponseEntity.status(200).body(products);
	}
	
	
	@PostMapping("/addcart") // user add product to their cart 
	public String addToCart(@RequestParam int id,@RequestParam int quantity,Authentication authentication) {
		productService.addToCart(id, quantity, authentication);
		return "Product Add on cart successfully";
	}
	
	@GetMapping("/getcart") // get all items from user cart
	
	public List<CartItem> getCart(Authentication authentication){
		return productService.getcart(authentication);
	}

	
	@PostMapping("/order") // place order 
	public Order order(Authentication authentication) {
		
		return productService.order(authentication);
	}
	@GetMapping("/getorders")
	public List<Order> getOrders(Authentication auth){
		return productService.getOrders(auth);
	}
	
	@GetMapping("/getorderitems") // To get user ordered  items
	public List<List<OrderItem>> getOrderItems(Authentication auth){
		return productService.getOrderItems(auth);
	}
	
}
