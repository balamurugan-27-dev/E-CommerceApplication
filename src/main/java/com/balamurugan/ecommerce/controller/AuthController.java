package com.balamurugan.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.dto.AuthResponse;
import com.balamurugan.ecommerce.service.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	AuthService authService;
	
	@GetMapping("/login")
	public String login(AuthResponse authResponse) {
		Authentication authentication=auth.authenticate(new UsernamePasswordAuthenticationToken(authResponse.getEmail(),authResponse.getPassword()));
		
		User user=(User)authentication.getPrincipal();
		String email=user.getUsername();
		return "token";
		
	}
	@PostMapping("signup")
	public void signup(@RequestBody AuthResponse authResponse) {
		authService.signup(authResponse);
	}
}
