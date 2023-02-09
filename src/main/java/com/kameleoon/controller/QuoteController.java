package com.kameleoon.controller;

import com.kameleoon.model.QuoteTimeStamp;
import com.kameleoon.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteRepository quoteRepository;

    @GetMapping
    public Timestamp getAllQuotes() {
        QuoteTimeStamp quoteTimeStamp = new QuoteTimeStamp();
        quoteTimeStamp.setPressedAt(new Timestamp(System.currentTimeMillis()));

        return null;
    }

    @PostMapping
    public Timestamp postQuotes() {
        QuoteTimeStamp quoteTimeStamp = new QuoteTimeStamp();
        quoteTimeStamp.setPressedAt(new Timestamp(System.currentTimeMillis()));

        quoteRepository.save(quoteTimeStamp);

        return new Timestamp(System.currentTimeMillis());
    }
}
