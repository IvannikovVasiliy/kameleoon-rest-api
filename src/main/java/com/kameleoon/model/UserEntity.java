package com.kameleoon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    public UserEntity(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<QuoteEntity> quoteEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ScoreEntity> scores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles"))
    private List<Role> roles;
}
