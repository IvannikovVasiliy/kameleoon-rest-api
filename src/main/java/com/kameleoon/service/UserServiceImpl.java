package com.kameleoon.service;

import com.kameleoon.model.Role;
import com.kameleoon.model.UserEntity;
import com.kameleoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);

        return new User(userEntity.getLogin(), userEntity.getPassword(), mappedRoles(userEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappedRoles(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> resultSet = new ArrayList<>();
        roles.stream().forEach(role -> resultSet.add(
                new SimpleGrantedAuthority(role.getName()))
        );

        return resultSet;
    }
}
