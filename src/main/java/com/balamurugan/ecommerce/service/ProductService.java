package com.balamurugan.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.balamurugan.ecommerce.model.Products;
import com.balamurugan.ecommerce.repository.ProductsRepo;

@Service
public class ProductService {
	@Autowired
	ProductsRepo productsRepo;
	
	
	public List<Products> getProducts(){
		return productsRepo.findAll();
	}
	

	public Products addProduct(Products product) {
		return productsRepo.save(product);
	}


	public void deleteProduct(int id) {
		
		 productsRepo.deleteById(id);
	}
	
	
	
}
