package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserModel {
    private String email;
    private String login;
    private String password;
    private List<String> quotes;
    private String[] roles;
}
