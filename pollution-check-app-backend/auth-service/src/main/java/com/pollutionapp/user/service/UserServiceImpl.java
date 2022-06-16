package com.pollutionapp.user.service;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.repository.UserRepository;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public User updatePassword(User user,String password) {
        user.setPassword(password);
        return  userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmailAndPassword(String userEmail, String password) throws UserNotFoundException {
        User user = userRepository.findUserByEmailAndPassword(userEmail, password);
        if(user != null)
            return user;
        return null;
    }


    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> isUserExists = userRepository.findById(user.getEmail());
        if(isUserExists.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        else{
            throw new UserAlreadyExistsException("User Already Exists.");
        }
    }
}
