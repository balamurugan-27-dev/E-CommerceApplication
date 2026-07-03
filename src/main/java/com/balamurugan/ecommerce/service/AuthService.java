package com.balamurugan.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.Exceptions.ResourceNotFoundException;
import com.balamurugan.ecommerce.dto.AuthResponse;
import com.balamurugan.ecommerce.jwt.JwtUtil;
import com.balamurugan.ecommerce.model.Roles;
import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.RolesRepo;
import com.balamurugan.ecommerce.repository.UserRepo;

@Service
public class AuthService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	RolesRepo rolesRepo;
	
	@Autowired
	JwtUtil jwtUtil;
	
	PasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	
	public void signup(AuthResponse authResponse) {
		
		Users user=new Users();
		user.setEmail(authResponse.getEmail());
		user.setPassword(encoder.encode(authResponse.getPassword()));
		user.setName(authResponse.getName());
		Roles role=rolesRepo.findByRole(authResponse.getRole()).orElseThrow(()-> new ResourceNotFoundException("Role Not Found"));
		
		
		user.setRole(role);
		
		userRepo.save(user);
	}
	
	public String login(String Token) {
		return jwtUtil.generateToken(Token);
		
	}
	
}
