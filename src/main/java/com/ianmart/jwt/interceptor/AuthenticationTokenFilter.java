package com.ianmart.jwt.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ianmart.jwt.security.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserDetailsService userDetailsService;
	private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	public AuthenticationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = request.getHeader(this.tokenHeader);
		
		if (authToken != null && authToken.length() > 7) {
		//	cut 7 char string because "Bearer " schema is standard appended in JWT token	
		//	authToken = authToken.substring(7);

			String username = jwtTokenUtil.getUsernameFromToken(authToken);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				boolean isValid = jwtTokenUtil.validateToken(authToken);
				
				if (isValid) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					log.debug("Auth is valid: " + isValid);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
