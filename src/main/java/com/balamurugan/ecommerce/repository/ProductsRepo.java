package com.balamurugan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.Products;
@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {

}
