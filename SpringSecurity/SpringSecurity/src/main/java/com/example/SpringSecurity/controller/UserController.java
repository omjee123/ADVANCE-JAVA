package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.dto.LoginDTO;
import com.example.SpringSecurity.dto.TokenDTO;
import com.example.SpringSecurity.dto.UserDTO;
import com.example.SpringSecurity.entity.User;
import com.example.SpringSecurity.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {

        User user = userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public TokenDTO loginUser(@RequestBody LoginDTO loginDTo){
      return userService.loginUser(loginDTo);
    }
    }
