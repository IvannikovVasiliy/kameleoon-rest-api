package com.kameleoon.service;

import com.kameleoon.model.Role;
import com.kameleoon.model.UserEntity;
import com.kameleoon.model.UserModel;
import com.kameleoon.repository.RoleRepository;
import com.kameleoon.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserModel> getAllAuthors() {
        List<UserEntity> userEntities = userRepository.findAll();

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

    public String authenticate(UserModel userModel) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getLogin(),
                        userModel.getPassword()
                )
        );

        var userEntity = userRepository.findByLogin(userModel.getLogin());
        User user = new User(userEntity.getLogin(), userEntity.getPassword(), mappedRoles(userEntity.getRoles()));

        var jwtToken = jwtService.generateToken(user);

        return jwtToken;
    }

    @Transactional
    public String createUser(UserModel userModel) {
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

        User user = new User(userEntity.getLogin(), userEntity.getPassword(), mappedRoles(userEntity.getRoles()));

        String jwt = jwtService.generateToken(user);

        return jwt;
    }

    private Collection<? extends GrantedAuthority> mappedRoles(List<Role> roles) {
        Collection<SimpleGrantedAuthority> resultSet = new ArrayList<>();
        roles.stream().forEach(role -> resultSet.add(
                new SimpleGrantedAuthority(role.getName()))
        );
        System.out.println(resultSet.size());

        return resultSet;
    }

    public void editUserById(Long id, UserModel userModel) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setLogin(userModel.getLogin());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));

        if (userModel.getRoles() != null) {
            List<Role> roles = new ArrayList<>();
            for (var r : userModel.getRoles()) {
                Role role = roleRepository.findByName(r);
                roles.add(role);
            }
            userEntity.setRoles(roles);
        }

        userRepository.save(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
