package com.pollutionapp.user.service;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;
import com.pollutionapp.user.repository.UserAuthRepository;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	private UserAuthRepository userAuthRepo;

	public UserAuthServiceImpl(UserAuthRepository userAuthRepo) {
		super();
		this.userAuthRepo = userAuthRepo;
	}
	
	/*
	 * This method should be used to find an existing User with correct password.
	 */

	@Override
    public User findUserByEmailAndPassword(String userEmail, String password) throws UserNotFoundException {
		User user = userAuthRepo.findUserByEmailAndPassword(userEmail, password);
		if(user != null)
			return user;
		return null;
    }

	/*
	 * This method should be used to save a new User.
	 */
    
    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
    	Optional<User> isUserExists = userAuthRepo.findById(user.getEmail());
    	if(isUserExists.isEmpty()) {
    		userAuthRepo.save(user);
    		return true;
    	}
    	else{
    		throw new UserAlreadyExistsException("User Already Exists.");
    	}
    }
}
