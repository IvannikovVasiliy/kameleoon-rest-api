package com.kameleoon.model;

public class UserDto {
    public String email;
    public String login;

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.login = user.getLogin();
        return userDto;
    }

    public User toAuthor() {
        return new User(email, login);
    }
}
