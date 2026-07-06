package com.balamurugan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.model.Roles;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	@GetMapping("/getrole")
	public List<Roles> getRole(){
		return roleService.getRole();
	}
	
	@PostMapping("/addrole") // add new role
	public Roles addRole(@RequestParam String role) {
		return roleService.addRole(role);
	}
	
	@GetMapping("/getusers")
	public List<Users> getusers(@RequestParam int id){
		return roleService.getusers(id);
	}
}
