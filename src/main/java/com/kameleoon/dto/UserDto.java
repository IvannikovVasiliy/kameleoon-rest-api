package com.kameleoon.dto;

import com.kameleoon.entity.UserEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserDto {
    public String email;
    public String login;
    public Timestamp updatedAt;
    public List<String> quotes;
    public List<String> roles;

    public static UserDto toDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.email = userEntity.getEmail();
        userDto.login = userEntity.getLogin();
        userDto.updatedAt = userEntity.getUpdatedAt();
        userDto.roles = userEntity.getRoles().stream().map(r -> r.getName()).toList();
        userDto.quotes = userEntity.getQuoteEntities().stream().map(r -> r.getContent()).toList();
        return userDto;
    }
}
