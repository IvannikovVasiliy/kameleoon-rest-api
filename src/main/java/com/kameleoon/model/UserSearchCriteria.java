package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchCriteria {
    private String email;
    private String login;
}
