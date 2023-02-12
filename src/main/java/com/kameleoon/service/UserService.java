package com.kameleoon.service;

import com.kameleoon.entity.Role;
import com.kameleoon.model.ResponseUserModel;
import com.kameleoon.model.ResponseUserModifModel;
import com.kameleoon.model.UserModel;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {
    List<ResponseUserModel> getAllUsers();
    String authenticate(UserModel userModel);
    ResponseUserModifModel createUser(UserModel userModel);
    ResponseUserModifModel editUserById(Long id, UserModel userModel);
    String deleteById(Long id);

    ResponseUserModel getUserById(Long id);
}
