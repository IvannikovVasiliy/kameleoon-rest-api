package com.kameleoon.service;

import com.kameleoon.model.UserEntity;
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
        UserEntity userEntity = new UserEntity(userModel.getLogin(), userModel.getEmail());
        userRepository.save(userEntity);

        return true;
    }

    public List<UserModel> getAllAuthors() {
        List<UserEntity> userEntities = userRepository.findAll();

        System.out.println(userEntities.size());

        List<UserModel> res = new ArrayList<>();
        for (var author : userEntities) {
            UserModel userModel = UserModel
                    .builder()
                    .email(author.getEmail())
                    .login(author.getLogin())
                    .quotes(author.getQuoteEntities()
                            .stream()
                            .map(quote -> quote.getContent())
                            .toList())
                    .build();

            res.add(userModel);
        }

        return res;
    }
}
