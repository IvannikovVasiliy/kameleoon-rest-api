package com.kameleoon.controller;

import com.kameleoon.model.QuoteModel;
import com.kameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public List<QuoteModel> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @PostMapping
    public String postQuotes(@RequestBody QuoteModel quoteModel) {
        quoteService.createQuote(quoteModel);

        return "Quote is CREATED";
    }
}
