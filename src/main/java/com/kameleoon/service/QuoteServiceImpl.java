package com.kameleoon.service;


import com.kameleoon.dto.PageDto;
import com.kameleoon.dto.QuoteDto;
import com.kameleoon.entity.QuoteEntity;
import com.kameleoon.entity.UserEntity;
import com.kameleoon.model.*;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.specification.QuoteSpecification;
import com.kameleoon.specification.UserSearchCriteria;
import com.kameleoon.specification.UserSpecification;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public List<QuoteDto> getAllQuotes(PageDto pageDto) {
        Page<QuoteEntity> quotes = quoteRepository.findAll(pageDto.pageable());

        return quotes.stream().map(quote -> QuoteDto.toDto(quote)).toList();
    }

    private double averageScore(QuoteEntity quote) {
        int sum = 0;
        int count = 0;
        for (var score : quote.getScores()) {
            sum += score.getScore();
            count++;
        }

        if (count != 0) {
            return (double) sum / count;
        } else {
            return 0;
        }
    }

    public boolean createQuote(String content, HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String login;
        jwt = authHeader.substring(7);
        login = jwtService.extractUsername(jwt);

        UserEntity userEntity = userRepository.findByLogin(login);
        QuoteEntity quoteEntity = new QuoteEntity(content, userEntity);
        quoteRepository.save(quoteEntity);

        return true;
    }

    public QuoteModel getQuoteById(Long id) {
        QuoteEntity quoteEntity = quoteRepository.findById(id).get();

        return QuoteModel.fromEntity(quoteEntity);
    }

    public List<QuoteDto> getQuotesByParams(String content, UserModel userModel) {
        List<QuoteEntity> quoteEntities = quoteRepository.findAll(new QuoteSpecification(content));

        UserSearchCriteria userSearchCriteria = UserSearchCriteria
                .builder()
                .login(userModel.getLogin())
                .email(userModel.getEmail())
                .build();
        List<UserEntity> userEntities = userRepository.findAll(new UserSpecification(userSearchCriteria));

        return quoteEntities.stream()
                .filter(quote -> userEntities.contains(quote.getUser()))
                .map(quote -> QuoteDto.toDto(quote))
                .toList();
    }

    public QuoteModel getRandomQuote() {
        Long randomValue = quoteRepository.getRandomId();
        QuoteEntity quoteEntity = quoteRepository.findById(randomValue).get();

        return QuoteModel.fromEntity(quoteEntity);
    }

    public boolean editQuote(Long id, String content) {
        QuoteEntity quoteEntity = quoteRepository.findById(id).get();
        quoteEntity.setContent(content);

        quoteRepository.save(quoteEntity);

        return true;
    }

    public String deleteById(Long id) {
        String nameDeletedQuote = quoteRepository.findById(id).get().getContent();
        quoteRepository.deleteById(id);

        return nameDeletedQuote;
    }
}
