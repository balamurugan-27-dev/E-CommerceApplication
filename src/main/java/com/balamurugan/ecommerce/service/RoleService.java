package com.balamurugan.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.dto.UserResponse;
import com.balamurugan.ecommerce.model.Roles;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.RolesRepo;

@Service
public class RoleService {
	@Autowired
	RolesRepo roleRepo;
	public List<UserResponse> getUsers(int id){
		Roles role =roleRepo.findById(id).get();
		
		
		List<UserResponse>list=new ArrayList<UserResponse>();
		
		for(Users user : role.getUsers()) {
			UserResponse usr=new UserResponse();
			usr.setId(user.getId());
			usr.setName(user.getName());
			usr.setEmail(user.getEmail());
			list.add(usr);
		}
		
		return list;
	}
}
