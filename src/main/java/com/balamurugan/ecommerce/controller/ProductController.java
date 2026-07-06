package com.balamurugan.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	
	
	@PostMapping("/addproduct") // add to new product for sale 
	public Products addProduct(@RequestBody Products product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/deleteproduct") // delete product from list
	public String deleteProduct(@RequestParam int id ) {
		productService.deleteProduct(id);
		return "Delete Successfull";
	}

}
