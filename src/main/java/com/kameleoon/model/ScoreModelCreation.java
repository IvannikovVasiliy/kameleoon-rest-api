package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ScoreModelCreation {
    private int score;
    private UserDto userDto;
    private Timestamp modifyAt;
    private Long quoteId;
}
