package com.pollutionapp.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.service.UserAuthService;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAuthController {

	static final long EXPIRATIONTIME = 300000;
	private Map<String, String> map = new HashMap<>();
	@Autowired
	private UserAuthService userAuthService;
	
    public UserAuthController(UserAuthService userAuthService) {
    	this.userAuthService = userAuthService;
	}

    @PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws UserNotFoundException {
    	try {
    		User userById = userAuthService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    		if(userById == null) {
    			userAuthService.saveUser(user);

    			return new ResponseEntity<User>(user, HttpStatus.CREATED);
    		}
    	}catch(UserAlreadyExistsException e) {

    		return new ResponseEntity<User>(HttpStatus.CONFLICT);
    	} 

    	return new ResponseEntity<User>(HttpStatus.CONFLICT);
	}

    @PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException{
    	String jwtToken = "";
    	try {
    		jwtToken = getToken(user.getEmail(), user.getPassword());
    		map.clear();
    		map.put("message", "user successfully logged in");
    		map.put("token", jwtToken);
    	}
    	catch(Exception e) {
    		String exceptionMsg = e.getMessage();
    		map.clear();
    		map.put("token", null);
    		map.put("message", exceptionMsg);

    		return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    	}

    	return new ResponseEntity<>(map, HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200",exposedHeaders = "Access-Control-Allow-Origin")
	@PostMapping(value = "/authenticate")
	public ResponseEntity<Map<String, String>> authenticateUser(
			@RequestHeader("Authorization") String token) {

		Map<String, String> response = new HashMap<>();
		if (!validateToken(token.substring(7))) {
			response.put("message", "Invalid token");
			response.put("status", HttpStatus.UNAUTHORIZED.toString());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		response.put("message", "Valid token");
		response.put("status", HttpStatus.OK.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
    
    public String getToken(String userEmail, String password) throws Exception{
    	if(userEmail == null || password == null) {
    		throw new ServletException("Please fill in username and password.");
    	}
    	
    	User isUserExists = userAuthService.findUserByEmailAndPassword(userEmail, password);
    	
    	if(isUserExists == null ) {
    		throw new ServletException("Invalid Credentials.");
    	}
    	
    	String jwtToken = Jwts.builder().setSubject(userEmail)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
    			.signWith(SignatureAlgorithm.HS256, "secretkey")
    			.compact();

    	return jwtToken;
    }

	public boolean validateToken(String token) {
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if (claims != null) {
			return true;
		}
		return false;
	}
}
