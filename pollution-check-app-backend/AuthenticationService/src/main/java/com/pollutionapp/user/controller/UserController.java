package com.pollutionapp.user.controller;

import com.pollutionapp.user.model.User;
import com.pollutionapp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        Optional<User> user = userService.getByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/")
    public ResponseEntity<User> changePassword(@RequestBody User user){
        User updateUser = userService.updatePassword(user);
        if(updateUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }
}
