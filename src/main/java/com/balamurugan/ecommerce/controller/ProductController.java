package com.balamurugan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/getproducts")
	public List<Products> get(){
		return productService.getProducts();
	}
	
	@PostMapping("/addproduct")
	public Products addProduct(@RequestBody Products product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/deleteproduct")
	public String deleteProduct(@RequestParam int id ) {
		productService.deleteProduct(id);
		return "Delete Successfull";
	}
	
	
	
}
