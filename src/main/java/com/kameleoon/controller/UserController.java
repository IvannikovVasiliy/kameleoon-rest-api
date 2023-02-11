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
        String jwt = userService.createUser(userModel);

        return jwt;
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody UserModel userModel) {
        return userService.authenticate(userModel);
    }

    @PatchMapping("/{id}/edit")
    public String editUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        userService.editUserById(id, userModel);
        return "The User is EDITED";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "The User is DELETED";
    }
}
