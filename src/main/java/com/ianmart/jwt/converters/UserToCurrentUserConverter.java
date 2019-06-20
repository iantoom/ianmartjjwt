package com.ianmart.jwt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ianmart.jwt.domains.User;
import com.ianmart.jwt.models.CurrentUser;
import com.ianmart.jwt.models.CurrentUser.Name;

@Component
public class UserToCurrentUserConverter implements Converter<User, CurrentUser> {

	@Override
	public CurrentUser convert(User source) {
		
		if (source == null) {
			return null;
		}
		final CurrentUser currentUser = new CurrentUser();
		
		currentUser.setId(source.getId());
		currentUser.setEmail(source.getEmail());
		
		Name name = currentUser.new Name();
		name.setFirstName(source.getFirstName());
		name.setMiddleName(source.getMiddleName());
		name.setLastName(source.getLastName());
		currentUser.setName(name);
		
		currentUser.setPicture(source.getPicture());
		currentUser.setRole(source.getRole().toLowerCase());
		currentUser.setDateOfBirth(source.getDateOfBirth());
		currentUser.setAddress(source.getAddress());
		currentUser.setPhones(source.getPhoneNumbers());
		return currentUser;
	}

}
