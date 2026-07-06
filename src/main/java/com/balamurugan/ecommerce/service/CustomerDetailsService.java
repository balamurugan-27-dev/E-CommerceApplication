package com.balamurugan.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.balamurugan.ecommerce.model.Users;
import com.balamurugan.ecommerce.repository.UserRepo;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByEmail(username)
		        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().getRole())
				.build();
	}
	
}
