package com.ianmart.jwt.converters;

import org.springframework.core.convert.converter.Converter;

import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.models.CurrentUser;

public class CurrentUserToUserConverter implements Converter<CurrentUser, User> {

	@Override
	public User convert(CurrentUser source) {
		User user = new User();
		user.setId(source.getId());
		user.setFirstName(source.getName().getFirstName());
		user.setMiddleName(source.getName().getMiddleName());
		user.setLastName(source.getName().getLastName());
		user.setPicture(source.getPicture());
		user.setEmail(source.getEmail());
		user.setRole(user.getRole());
		user.setDateOfBirth(source.getDateOfBirth());
		user.setAddress(source.getAddress());
		user.setCreatedDate(source.getCreatedDate());
		return user;
	}

}
