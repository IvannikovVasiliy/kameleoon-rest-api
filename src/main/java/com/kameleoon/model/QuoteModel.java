package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class QuoteModel {
    private String content;
    private Timestamp modifyAt;
    private UserDto userDto;
    private double score;
}
