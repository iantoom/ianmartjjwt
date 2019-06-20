package com.ianmart.jwt.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ianmart.jwt.converters.UserToCurrentUserConverter;
import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.models.CurrentUser;
import com.ianmart.jwt.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private UserRepository userRepository;
	
	//auto wiring password encoder from Security Configuration Bean
	private PasswordEncoder passwordEncoder;
	private UserToCurrentUserConverter userToCurrentUserConverter;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			UserToCurrentUserConverter userToCurrentUserConverter) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userToCurrentUserConverter = userToCurrentUserConverter;
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findUserById(String userId) {
		
		Optional<User> userOptional= userRepository.findById(Long.valueOf(userId));
		
		if (userOptional.isEmpty()) {
			log.error("Cannot find user with ID: " + userId);

			return new User();
		}
		
		return userOptional.get();
	}

	@Override
	public User saveUser(User user) {
		User userSaved = user;
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		userSaved.setPassword(encodedPassword);
		userSaved.setCreatedDate(new Date());
		
		return userRepository.save(userSaved);
	}
	
	@Override
	public CurrentUser putUser(String userId, CurrentUser currentUser) {
		User user = userRepository.findById(Long.valueOf(userId)).get();
		
		if (currentUser.getName().getFirstName() != null) {
			user.setFirstName(currentUser.getName().getFirstName());
		}
		if (currentUser.getName().getMiddleName() != null) {
			user.setMiddleName(currentUser.getName().getMiddleName());
		}
		if (currentUser.getName().getLastName() != null) {
			user.setLastName(currentUser.getName().getLastName());
		}
		if (currentUser.getPicture() != null) {
			user.setPicture(currentUser.getPicture());
		}
		if (currentUser.getEmail() != null) {
			user.setEmail(currentUser.getEmail());
		}
		if (currentUser.getDateOfBirth() != null) {
			user.setDateOfBirth(currentUser.getDateOfBirth());
		}
		if (currentUser.getAddress() != null) {
			user.setAddress(currentUser.getAddress());
		}
		if (currentUser.getPhones() != null) {
			user.getPhoneNumbers().clear();
			user.getPhoneNumbers().addAll(currentUser.getPhones());
		}
		if (currentUser.getCreatedDate() != null) {
			user.setCreatedDate(currentUser.getCreatedDate());
		}
		
		User returnedUser = userRepository.save(user);
		
		return userToCurrentUserConverter.convert(returnedUser);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmailIgnoreCase(email);
	}

	@Override
	@Transactional(readOnly=true)
	public CurrentUser findCurrentUserById(String userId) {
		
		return userToCurrentUserConverter.convert(this.findUserById(userId));
	}
}
