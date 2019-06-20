package com.ianmart.jwt.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.repositories.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	public JwtUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmailIgnoreCase(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cannot found user with username: " + username);
		}else {
			return JwtUserFactory.create(user);
		}
	}
	
	
	
}
