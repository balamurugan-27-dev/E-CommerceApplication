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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.dto.AuthResponse;
import com.balamurugan.ecommerce.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager auth;
	
	@Autowired
	AuthService authService;
	
	
	@PostMapping("/signup")
	public void signup(@RequestBody AuthResponse authResponse) {
		authService.signup(authResponse);
	}
	
	@GetMapping("/login")
	public String login(@RequestParam String inemail,@RequestParam String password) {
		System.out.println(inemail);
		Authentication authentication=auth.authenticate(new UsernamePasswordAuthenticationToken(inemail,password));
		
		User user=(User)authentication.getPrincipal();
		String email=user.getUsername();
		return authService.login(email);
		
	}
}
