package com.balamurugan.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.model.Roles;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.RolesRepo;
import com.balamurugan.ecommerce.repository.UserRepo;

@Service
public class RoleService {
	@Autowired
	RolesRepo roleRepo;
	
	@Autowired
	UserRepo userRepo;
	public List<Roles> getRole(){
		
		return roleRepo.findAll();
	}
	public Roles addRole(String role) {
		Roles newRole =new Roles();
		newRole.setRole(role);
		
		
		
		return roleRepo.save(newRole);
		}
	public List<Users> getusers(int id) {
		
		Roles role=roleRepo.findById(id).get();
		
		return role.getUsers();
		
		
	}
}
