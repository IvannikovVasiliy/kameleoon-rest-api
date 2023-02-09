package com.kameleoon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
public class Author {

    public Author(String surname, String name, String pseudonym) {
        this.surname = surname;
        this.name = name;
        this.pseudonym = pseudonym;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;
    private String name;
    private String pseudonym;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Quote> quotes;
}
