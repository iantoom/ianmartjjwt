package com.ianmart.jwt.services;

import java.util.List;

import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.models.CurrentUser;

public interface UserService {

	User saveUser(User user);
	
	List<User> findAll();
	
	User getUserByEmail(String email);
	
	public User findUserById(String userId);
	
	CurrentUser findCurrentUserById(String userId);
	
	CurrentUser putUser(String email, CurrentUser currentUser);
}
