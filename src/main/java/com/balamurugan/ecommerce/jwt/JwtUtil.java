package com.balamurugan.ecommerce.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String secret_key="secretkeyissecretkeyissecretkey1";
	
	private static final SecretKey secretKey=Keys.hmacShaKeyFor(secret_key.getBytes());
	
	
	public String generateToken(String mail) {
		
		return Jwts.builder()
		.subject(mail)
		.signWith(secretKey)
		.issuedAt( new Date())
		.expiration(new Date(System.currentTimeMillis()+1000*(60*60)))
		.compact();
	}
	
	public Claims extractAllClaims(String token) {
		
		return Jwts.parser()
		.verifyWith(secretKey)
		.build()
		.parseSignedClaims(token)
		.getPayload();
	}
	
	public <T> T extractClaim(String token,Function<Claims, T> function) {
		
		Claims claim=extractAllClaims(token);
		return function.apply(claim);
		
	}
	
	
	
	public boolean tokenValidation(String token) {
		
		Date expiry=extractClaim(token, (c)->c.getExpiration());
		return new Date().before(expiry);
		
	}
	
	
	
	
	
	
	
	
	
}
