package com.kameleoon.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TimestampModel {
    private String quoteText;
    private String userLogin;
    private int score;
}
