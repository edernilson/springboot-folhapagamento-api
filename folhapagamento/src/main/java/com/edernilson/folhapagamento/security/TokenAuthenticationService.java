package com.edernilson.folhapagamento.security;

import static com.edernilson.folhapagamento.config.ApplicationConstants.EXPIRATION_TIME;
import static com.edernilson.folhapagamento.config.ApplicationConstants.HEADER_NAME;
import static com.edernilson.folhapagamento.config.ApplicationConstants.KEY;
import static com.edernilson.folhapagamento.config.ApplicationConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    static void addAuthentication(HttpServletResponse response, String username) throws IOException {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
		String body = "{\"token\": \""+TOKEN_PREFIX + " " + JWT + "\"}";
		response.getWriter().write(body);
        response.getWriter().flush();
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);
		
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(KEY)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

					
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}
	
    
}
