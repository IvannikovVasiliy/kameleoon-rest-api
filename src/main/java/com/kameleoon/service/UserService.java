package com.kameleoon.service;

import com.kameleoon.model.User;
import com.kameleoon.model.UserModel;
import com.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean createUser(UserModel userModel) {
        User user = new User(userModel.getLogin(), userModel.getPassword());
        userRepository.save(user);

        return true;
    }
}
