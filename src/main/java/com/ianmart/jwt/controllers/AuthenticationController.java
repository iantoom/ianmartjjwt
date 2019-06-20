package com.ianmart.jwt.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.exceptions.UnauthorizedException;
import com.ianmart.jwt.models.TokenDTO;
import com.ianmart.jwt.security.JwtTokenUtil;
import com.ianmart.jwt.security.JwtUser;

@RestController
public class AuthenticationController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenUtil jwtTokenUtil;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@PostMapping("/v1/login")
	public ResponseEntity<TokenDTO> login(@RequestBody User user,
			HttpServletRequest request, HttpServletResponse resopnse) {
		
		log.debug("logging in with username: " + user.getEmail() + " and password: " + user.getPassword());

		try {
			Authentication authentication =
					authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			
			final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token = jwtTokenUtil.generateToken(userDetails);
			
			return new ResponseEntity<TokenDTO>(new TokenDTO(token), HttpStatus.OK);
			
		} catch (Exception e) {
			throw new UnauthorizedException(e.getMessage());
		} 
	}
}
