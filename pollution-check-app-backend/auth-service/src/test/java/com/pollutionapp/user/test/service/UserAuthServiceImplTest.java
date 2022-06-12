package com.pollutionapp.user.test.service;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.repository.UserRepository;
import com.pollutionapp.user.service.UserAuthServiceImpl;
import com.pollutionapp.user.util.exception.UserAlreadyExistsException;
import com.pollutionapp.user.util.exception.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserAuthServiceImplTest {

    @Mock
    private UserRepository userAuthRepository;

    private User user;
    @InjectMocks
    private UserAuthServiceImpl userAuthServiceImpl;

    Optional<User> optional;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setEmail("Jhon123@gmail.com");
        user.setName("John");
        user.setPassword("123456");
        user.setConfirmPassword("123456");
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistsException {

        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
        boolean flag = userAuthServiceImpl.saveUser(user);
        assertEquals(true, flag);
    }

    @Test
    public void testSaveUserFailure() {

        Mockito.when(userAuthRepository.findById("Jhon123@gmail.com")).thenReturn(optional);
        Mockito.when(userAuthRepository.save(user)).thenReturn(user);
        assertThrows(
        		UserAlreadyExistsException.class,
                    () -> { userAuthServiceImpl.saveUser(user); });

    }

    @Test
    public void testFindByUserIdAndPassword() throws UserNotFoundException {
        Mockito.when(userAuthRepository.findUserByEmailAndPassword("Jhon123@gmail.com", "123456")).thenReturn(user);
        User fetchedUser = userAuthServiceImpl.findUserByEmailAndPassword("Jhon123@gmail.com", "123456");
        assertEquals("Jhon123@gmail.com", fetchedUser.getEmail());
    }
}
