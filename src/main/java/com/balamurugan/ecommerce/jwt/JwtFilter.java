package com.balamurugan.ecommerce.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.balamurugan.ecommerce.repository.UserRepo;
import com.balamurugan.ecommerce.service.CustomerDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CustomerDetailsService customerService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header=request.getHeader("Authorization");
		
		//System.out.println(header);
		
		if(header==null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token=header.substring(7);
		System.out.println(token);
		String userMail=jwtUtil.extractClaim(token,(claim)->claim.getSubject());
		try {
		
		if(userMail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails user=customerService.loadUserByUsername(userMail);
			System.out.println(SecurityContextHolder.getContext().getAuthentication());
			if(jwtUtil.tokenValidation(token)) {
				
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
						user,null,user.getAuthorities());
				
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
			else {
				response.setStatus(404);
			}
			
		}
		}
		catch(Exception e) {
			response.setStatus(401);
			return;
		}
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		filterChain.doFilter(request, response);
		
		
	}
	
}
