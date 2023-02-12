package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ResponseUserModifModel {
    private String login;
    private String email;
    private Timestamp updatedAt;
    private List<String> roles;
}
