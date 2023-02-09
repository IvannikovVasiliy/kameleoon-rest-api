package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorSearchCriteria {
    private String name;
    private String surname;
    private String pseudonym;
}
