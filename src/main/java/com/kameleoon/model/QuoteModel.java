package com.kameleoon.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteModel {
    private String text;
    private AuthorDto author;
}
