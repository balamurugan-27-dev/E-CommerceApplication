package com.balamurugan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.dto.UserResponse;
import com.balamurugan.ecommerce.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	@GetMapping("/getuser")
	public List<UserResponse> getUsers(@RequestParam int id){
		return roleService.getUsers(id);
	}
	
}
