package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class AuthorModel {
    private String name;
    private String surname;
    private String pseudonym;
    private List<String> quotes;
}
