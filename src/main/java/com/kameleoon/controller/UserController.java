package com.kameleoon.controller;

import com.kameleoon.model.User;
import com.kameleoon.model.UserModel;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUser(@RequestBody UserModel userModel) {
        userService.createUser(userModel);

        return "User is CREATED";
    }
}
