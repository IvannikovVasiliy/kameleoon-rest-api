package com.kameleoon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Quote {

    @Id
    private Long id;

    private String text;
    private Long score;
}
