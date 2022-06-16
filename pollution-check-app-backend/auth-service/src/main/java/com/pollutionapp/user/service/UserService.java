package com.pollutionapp.user.service;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getByEmail(String email);

    public User updatePassword(User user,String password);

    public User save(User user);

    public User findUserByEmailAndPassword(String userEmail, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;
}
