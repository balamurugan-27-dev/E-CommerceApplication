package com.balamurugan.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balamurugan.ecommerce.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);
	//List<Users> findByRoleRole(String name);
}