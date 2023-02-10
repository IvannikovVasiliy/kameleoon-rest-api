package com.kameleoon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class User {

    public User(String login, String email) {
        this.login = login;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Quote> quotes;
}
