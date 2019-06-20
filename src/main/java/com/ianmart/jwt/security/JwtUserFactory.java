package com.ianmart.jwt.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ianmart.jwt.domains.User;

public class JwtUserFactory {

	public static JwtUser create(User user) {
		
		String userRole = user.getRole();
		
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), user,
				mapToGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE_" + userRole))), user.isEnabled());
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
	}
}
