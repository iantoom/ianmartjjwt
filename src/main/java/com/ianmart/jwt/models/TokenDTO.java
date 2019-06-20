package com.ianmart.jwt.models;

import java.io.Serializable;

public class TokenDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4662897293781858392L;
	private String accessToken;

	public TokenDTO(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getToken() {
		return accessToken;
	}

	public void setToken(String token) {
		this.accessToken = token;
	}
	
	
}
