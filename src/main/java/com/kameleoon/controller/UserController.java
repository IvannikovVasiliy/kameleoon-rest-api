package com.kameleoon.controller;

import com.kameleoon.model.ResponseUserModifModel;
import com.kameleoon.model.ResponseUserModel;
import com.kameleoon.model.UserModel;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ResponseUserModel>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseUserModel> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseUserModifModel> createUser(@RequestBody UserModel userModel) {

        ResponseUserModifModel response = userService.createUser(userModel);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserModel userModel) {
        String jwt = userService.authenticate(userModel);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ResponseUserModifModel> editUser(@PathVariable Long id, @RequestBody UserModel userModel) {
        ResponseUserModifModel response = userService.editUserById(id, userModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        String nameDeletedUser = userService.deleteById(id);
        return "The User \"" + nameDeletedUser + "\" is DELETED";
    }
}