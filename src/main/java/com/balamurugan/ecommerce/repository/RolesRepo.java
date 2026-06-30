package com.balamurugan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
	
}
