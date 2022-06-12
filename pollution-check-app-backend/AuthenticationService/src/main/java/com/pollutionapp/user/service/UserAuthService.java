package com.pollutionapp.user.service;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;

public interface UserAuthService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    public User findUserByEmailAndPassword(String userEmail, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
}
