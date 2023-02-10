package com.kameleoon.service;

import com.kameleoon.model.User;
import com.kameleoon.model.UserModel;
import com.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
//transactional
public class UserService {

    private final UserRepository userRepository;

    public boolean createAuthor(UserModel userModel) {
        User user = new User(userModel.getLogin(), userModel.getEmail());
        userRepository.save(user);

        return true;
    }

    public List<UserModel> getAllAuthors() {
        List<User> users = userRepository.findAll();

        List<UserModel> res = new ArrayList<>();
        for (var author : users) {
            UserModel userModel = UserModel
                    .builder()
                    .email(author.getEmail())
                    .login(author.getLogin())
                    .quotes(author.getQuotes()
                            .stream()
                            .map(quote -> quote.getContent())
                            .toList())
                    .build();

            res.add(userModel);
        }

        return res;
    }
}
