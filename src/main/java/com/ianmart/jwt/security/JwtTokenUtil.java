package com.ianmart.jwt.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	static final String CLAIM_KEY_AUTH_STATUS = "isAuthenticated";
	static final String CLAIM_KEY_ROLE = "userRole";
	static final String CLAIM_KEY_ID = "userId";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;

	public String getUsernameFromToken(String token) {
		String username = null;
		try {
				
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
			
			log.debug("token extracted of username: " + username);

		}catch (Exception e) {
			username = null;
		}
		return username;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			log.debug("claims extracted from token: " + claims.toString());

		}catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public boolean validateToken(String authToken) {
		return !isTokenExpired(authToken);
	}

	private boolean isTokenExpired(String authToken) {
		final Date expiration = getExpirationDateFromToken(authToken);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String authToken) {
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			if (claims != null) {
				expiration = claims.getExpiration();
			}else {
				expiration = null;
			}
			
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}

	public String generateToken(JwtUser userDetails) {
		Map<String, Object> customClaims = new HashMap<>();
		customClaims.put(CLAIM_KEY_AUTH_STATUS, true);
		customClaims.put(CLAIM_KEY_ROLE, userDetails.getUser().getRole().toLowerCase());
		customClaims.put(CLAIM_KEY_ID, userDetails.getId().toString());
	//	claims.put(CLAM_KEY_CREATED, new Date());
		return generateToken(customClaims, userDetails);
	}

	private String generateToken(Map<String, Object> customClaims, JwtUser userDetails) {
		
		return Jwts.builder()
				.setClaims(customClaims)
				.setSubject(userDetails.getUsername())
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

}
