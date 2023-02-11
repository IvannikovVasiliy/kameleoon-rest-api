package com.kameleoon.controller;

import com.kameleoon.model.*;
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
    public List<QuoteModel> getAllQuotes(@ModelAttribute PageDto pageDto) {
        return quoteService.getAllQuotes(pageDto);
    }

    @GetMapping("{id}")
    public QuoteModel getQuoteById(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }

    @GetMapping("/random")
    public QuoteModel getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("/params")
    public List<QuoteModel> getQuotesByParams(String content, @ModelAttribute UserModel userModel) {
        return quoteService.getQuotesByParams(content, userModel);
    }

    @PostMapping("/create")
    public String postQuotes(@RequestBody QuoteModel quoteModel) {
        quoteService.createQuote(quoteModel);
        return "Quote is CREATED";
    }

    @PatchMapping("{id}/edit")
    public String editQuoteById(@PathVariable Long id, String content) {
        quoteService.editQuote(id, content);

        return "Quote is EDITED";
    }

    @DeleteMapping("{id}/delete")
    public String deleteById(@PathVariable Long id) {
        quoteService.deleteById(id);

        return "Quote is DELETED";
    }
}
