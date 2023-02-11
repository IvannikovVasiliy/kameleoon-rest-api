package com.kameleoon.controller;

import com.kameleoon.model.UserModel;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public List<UserModel> getAllAuthors() {
        return userService.getAllAuthors();
    }

    @PostMapping("/register")
    public String createUser(@RequestBody UserModel userModel) {
        String jwt = userService.createAuthor(userModel);

        return jwt;
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody UserModel userModel) {
        return userService.authenticate(userModel);
    }
}
