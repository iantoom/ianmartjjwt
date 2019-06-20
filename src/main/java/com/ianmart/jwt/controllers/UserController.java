package com.ianmart.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ianmart.jwt.converters.UserToCurrentUserConverter;
import com.ianmart.jwt.models.CurrentUser;
import com.ianmart.jwt.services.UserService;

@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private UserService userService;
	private UserToCurrentUserConverter userToCurrentUserConverter;

	
	
	public UserController(UserService userService, UserToCurrentUserConverter userToCurrentUserConverter) {
		this.userService = userService;
		this.userToCurrentUserConverter = userToCurrentUserConverter;
	}

	@GetMapping("/v1/user/{userId}")
	@PreAuthorize("#userId == principal.id.toString()")
	public ResponseEntity<CurrentUser> getSelfUser(@PathVariable String userId) {
		
		log.debug("Controller retrieving Current User with " +
				"email: " + userId);

		CurrentUser currentUser = userToCurrentUserConverter.convert(userService.findUserById(userId));
		
		return new ResponseEntity<CurrentUser>(currentUser, HttpStatus.OK);
	}
	
	@PutMapping("/v1/user/{userId}")
	@PreAuthorize("#userId == principal.id.toString()")
	public ResponseEntity<CurrentUser> putSelfUser(@PathVariable String userId, 
			@RequestBody CurrentUser currentUser) {
		
		CurrentUser savedCurrentUser = userService.putUser(userId, currentUser);
		return new ResponseEntity<CurrentUser> (savedCurrentUser, HttpStatus.OK);
	}
}
