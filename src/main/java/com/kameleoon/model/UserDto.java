package com.kameleoon.model;

import lombok.Data;

@Data
public class UserDto {
    public String email;
    public String login;

    public static UserDto toDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.email = userEntity.getEmail();
        userDto.login = userEntity.getLogin();
        return userDto;
    }
}
