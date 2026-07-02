package com.balamurugan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.model.CartItem;
import com.balamurugan.ecommerce.model.Order;
import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/getproducts") // to get all products
	public List<Products> get(){
		return productService.getProducts();
	}
	
	@PostMapping("/addproduct") // add to new product for sale 
	public Products addProduct(@RequestBody Products product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/deleteproduct") // delete product from list
	public String deleteProduct(@RequestParam int id ) {
		productService.deleteProduct(id);
		return "Delete Successfull";
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
	
}
