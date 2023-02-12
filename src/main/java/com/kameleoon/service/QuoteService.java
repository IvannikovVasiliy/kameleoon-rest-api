package com.kameleoon.service;

import com.kameleoon.dto.PageDto;
import com.kameleoon.dto.QuoteDto;
import com.kameleoon.model.QuoteModel;
import com.kameleoon.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface QuoteService {
    List<QuoteDto> getAllQuotes(PageDto pageDto);
    boolean createQuote(String content, HttpServletRequest request);
    QuoteModel getQuoteById(Long id);
    List<QuoteDto> getQuotesByParams(String content, UserModel userModel);
    boolean editQuote(Long id, String content);
    QuoteModel getRandomQuote();
    String deleteById(Long id);
}
