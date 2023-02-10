package com.kameleoon.service;


import com.kameleoon.model.*;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.specification.QuoteSpecification;
import com.kameleoon.specification.UserSearchCriteria;
import com.kameleoon.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public List<QuoteModel> getAllQuotes(PageDto pageDto) {
        Page<QuoteEntity> quotes = quoteRepository.findAll(pageDto.pageable());

        List<QuoteModel> res = new ArrayList<>();
        // streams are slowly
        for (var q : quotes) {
            UserDto userDto = new UserDto();
            userDto.email = q.getUser().getEmail();
            userDto.login = q.getUser().getLogin();

            double averageScore = averageScore(q);

            QuoteModel quoteModel = QuoteModel
                    .builder()
                    .content(q.getContent())
                    .userDto(userDto)
                    .modifyAt(q.getModifyAt())
                    .score(averageScore)
                    .build();

            res.add(quoteModel);
        }

        return res;
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

    public boolean createQuote(QuoteModel quoteModel) {
        UserSearchCriteria userSearchCriteria = UserSearchCriteria
                .builder()
                .email(quoteModel.getUserDto().email)
                .login(quoteModel.getUserDto().login)
                .build();

        UserEntity userEntity = userRepository.findOne(new UserSpecification(userSearchCriteria)).get();
        QuoteEntity quoteEntity = new QuoteEntity(quoteModel.getContent(), userEntity);
        quoteRepository.save(quoteEntity);

        return true;
    }

    public QuoteModel getQuoteById(Long id) {
        QuoteEntity quoteEntity = quoteRepository.findById(id).get();

        return QuoteModel
                .builder()
                .content(quoteEntity.getContent())
                .userDto(UserDto.toDto(quoteEntity.getUser()))
                .modifyAt(quoteEntity.getModifyAt())
                .score(averageScore(quoteEntity))
                .build();
    }

    public List<QuoteModel> getQuotesByParams(String content, UserModel userModel) {
        List<QuoteEntity> quoteEntities = quoteRepository.findAll(new QuoteSpecification(content));

        UserSearchCriteria userSearchCriteria = UserSearchCriteria
                .builder()
                .login(userModel.getLogin())
                .email(userModel.getEmail())
                .build();
        List<UserEntity> userEntities = userRepository.findAll(new UserSpecification(userSearchCriteria));

        return quoteEntities.stream()
                .filter(quote -> userEntities.contains(quote.getUser()))
                .map(quote -> QuoteModel
                        .builder()
                        .content(quote.getContent())
                        .userDto(UserDto.toDto(quote.getUser()))
                        .build())
                .toList();
    }

    public QuoteModel getRandomQuote() {
        Long size = quoteRepository.getCount();
        Random random = new Random();
        Long randomValue = random.nextLong(size)+1;

        QuoteEntity quoteEntity = quoteRepository.findById(randomValue).get();
        return QuoteModel
                .builder()
                .content(quoteEntity.getContent())
                .userDto(UserDto.toDto(quoteEntity.getUser()))
                .build();
    }

    public boolean editQuote(Long id, String content) {
        QuoteEntity quoteEntity = quoteRepository.findById(id).get();
        quoteEntity.setContent(content);

        quoteRepository.save(quoteEntity);

        return true;
    }

    public void deleteById(Long id) {
        quoteRepository.deleteById(id);
    }
}
