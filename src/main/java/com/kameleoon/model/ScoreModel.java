package com.kameleoon.model;

import com.kameleoon.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ScoreModel {
    private int score;
    private UserDto userDto;
    private Timestamp modifyAt;
    private String quoteContent;
}