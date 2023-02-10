package com.kameleoon.controller;

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
    public List<UserModel> getAllAuthors() {
        return userService.getAllAuthors();
    }

    @PostMapping
    public String createAuthor(@RequestBody UserModel userModel) {
        userService.createAuthor(userModel);

        return "Author CREATED";
    }
}
