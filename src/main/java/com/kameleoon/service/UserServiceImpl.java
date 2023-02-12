package com.kameleoon.service;

import com.kameleoon.entity.Role;
import com.kameleoon.entity.UserEntity;
import com.kameleoon.handler.ResourceAlreadyExistsException;
import com.kameleoon.handler.ResourceNotFoundException;
import com.kameleoon.model.*;
import com.kameleoon.repository.RoleRepository;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.specification.UserSearchCriteria;
import com.kameleoon.specification.UserSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<ResponseUserModel> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        List<ResponseUserModel> res = new ArrayList<>();
        for (var author : userEntities) {
            ResponseUserModel userModel = ResponseUserModel
                    .builder()
                    .email(author.getEmail())
                    .login(author.getLogin())
                    .updatedAt(author.getUpdatedAt())
                    .quotes(author.getQuoteEntities()
                            .stream()
                            .map(quote -> quote.getContent())
                            .toList())
                    .roles(author.getRoles().stream().map(r -> r.getName()).toList())
                    .build();

            res.add(userModel);
        }

        return res;
    }

    public String authenticate(UserModel userModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getLogin(),
                        userModel.getPassword()
                )
        );

        var userEntity = userRepository.findByLogin(userModel.getLogin()).get();
        User user = new User(userEntity.getLogin(), userEntity.getPassword(), mappedRoles(userEntity.getRoles()));

        var jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

    public ResponseUserModifModel createUser(UserModel userModel) {
        UserSearchCriteria userSearchCriteria = UserSearchCriteria
                .builder()
                .email(userModel.getEmail())
                .login(userModel.getLogin())
                .build();
        if (userRepository.exists(new UserSpecification(userSearchCriteria))) {
            throw new ResourceAlreadyExistsException("The usrer with such name already exists");
        }

        UserEntity userEntity = new UserEntity(
                userModel.getLogin(),
                userModel.getEmail(),
                passwordEncoder.encode(userModel.getPassword())
        );

        List<Role> roles = new ArrayList<>();
        for (var role : userModel.getRoles()) {
            Role r = roleRepository.findByName(role);
            roles.add(r);
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        return ResponseUserModifModel
                .builder()
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .updatedAt(userEntity.getUpdatedAt())
                .roles(userEntity.getRoles().stream().map(r -> r.getName()).toList())
                .build();
    }

    private Collection<? extends GrantedAuthority> mappedRoles(List<Role> roles) {
        Collection<SimpleGrantedAuthority> resultSet = new ArrayList<>();
        roles.stream().forEach(role -> resultSet.add(
                new SimpleGrantedAuthority(role.getName()))
        );
        System.out.println(resultSet.size());

        return resultSet;
    }

    public ResponseUserModifModel editUserById(Long id, UserModel userModel) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user with the id does not exist"));

        if (userModel.getLogin() != null) {
            userEntity.setLogin(userModel.getLogin());
        }
        if (userModel.getEmail() != null) {
            userEntity.setEmail(userModel.getEmail());
        }
        if (userModel.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        }
        if (userModel.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (var r : userModel.getRoles()) {
                Role role = roleRepository.findByName(r);
                roles.add(role);
            }
            userEntity.setRoles(roles);
        }

        userRepository.save(userEntity);

        return ResponseUserModifModel
                .builder()
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles().stream().map(r -> r.getName()).toList())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public String deleteById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user with the id does not exist"));

        String login = userEntity.getLogin();
        userRepository.deleteById(id);

        return login;
    }

    @Override
    public ResponseUserModel getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "The user with id=" + id + " does not exist."
        ));

        return ResponseUserModel
                .builder()
                .updatedAt(userEntity.getUpdatedAt())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .build();
    }
}
