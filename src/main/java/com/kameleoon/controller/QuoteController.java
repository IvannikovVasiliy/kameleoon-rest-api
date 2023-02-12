package com.kameleoon.controller;

import com.kameleoon.dto.PageDto;
import com.kameleoon.dto.QuoteDto;
import com.kameleoon.model.*;
import com.kameleoon.service.QuoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public List<QuoteDto> getAllQuotes(@ModelAttribute PageDto pageDto) {
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
    public List<QuoteDto> getQuotesByParams(String content, @ModelAttribute UserModel userModel) {
        return quoteService.getQuotesByParams(content, userModel);
    }

    @PostMapping("/create")
    public String postQuotes(String content, HttpServletRequest request) {
        quoteService.createQuote(content, request);
        return "Quote with content=\"" + content + "\" is ADDED";
    }

    @PatchMapping("{id}/edit")
    public String editQuoteById(@PathVariable Long id, String content) {
        quoteService.editQuote(id, content);

        return "Quote with content=\"" + content + " \" is EDITED";
    }

    @DeleteMapping("{id}/delete")
    public String deleteById(@PathVariable Long id) {
        String name = quoteService.deleteById(id);

        return "Quote \"" + name + "\" is DELETED";
    }
}
