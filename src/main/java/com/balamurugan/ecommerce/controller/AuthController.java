package com.balamurugan.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balamurugan.ecommerce.Exceptions.ResourceNotFoundException;
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
	
	@PostMapping("/login")
	public String login(@RequestBody AuthResponse response) {

		System.out.println("before");
		System.out.println(response.getEmail());
		System.out.println(response.getPassword());
	    try {
	        Authentication authentication = auth.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        response.getEmail(),
	                        response.getPassword()));
	        System.out.println("after");
	        User user=(User)authentication.getPrincipal();
	        return authService.login(user.getUsername());

	    } catch (Exception e) {
	       throw new ResourceNotFoundException("your user id or  password is wrong");
	    }
	}
}
