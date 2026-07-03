package com.balamurugan.ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.balamurugan.ecommerce.jwt.JwtFilter;
import com.balamurugan.ecommerce.service.CustomerDetailsService;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	CustomerDetailsService userService;
	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity request) {
		
		return request.csrf(csrf-> csrf.disable())
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(response->response
				.requestMatchers("/auth/login").permitAll()
				.anyRequest().authenticated())
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
		.build();

		
	}
	
	@Bean
	public AuthenticationProvider DaoAuthentication() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider(userService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	
	@Bean
	public AuthenticationManager authManager() {
		return new ProviderManager(DaoAuthentication());
	}
}
