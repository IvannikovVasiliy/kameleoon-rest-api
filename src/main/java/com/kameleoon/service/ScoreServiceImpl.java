package com.kameleoon.service;

import com.kameleoon.dto.UserDto;
import com.kameleoon.entity.QuoteEntity;
import com.kameleoon.entity.ScoreEntity;
import com.kameleoon.entity.UserEntity;
import com.kameleoon.model.*;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.repository.ScoreRepository;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.specification.UserSearchCriteria;
import com.kameleoon.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    @Cacheable(cacheNames = "scores")
    public List<ScoreModel> getAllScores() {
        List<ScoreEntity> scoreEntities = scoreRepository.findAll();

        List<ScoreModel> res = new ArrayList<>();
        for (var score : scoreEntities) {
            ScoreModel scoreModelCreation = ScoreModel
                    .builder()
                    .score(score.getScore())
                    .userDto(UserDto.toDto(score.getUser()))
                    .modifyAt(score.getModifyAt())
                    .quoteContent(score.getQuote().getContent())
                    .build();

            res.add(scoreModelCreation);
        }

        return res;
    }

    public boolean addScore(ScoreModelCreation scoreModelCreation) {
        UserSearchCriteria criteria = UserSearchCriteria
                .builder()
                .email(scoreModelCreation.getUserDto().email)
                .login(scoreModelCreation.getUserDto().login)
                .build();
        UserEntity user = userRepository.findOne(new UserSpecification(criteria)).get();

        QuoteEntity quote = quoteRepository.findById(scoreModelCreation.getQuoteId()).get();

        ScoreEntity scoreEntity = new ScoreEntity(scoreModelCreation.getScore(), user, quote);
        scoreRepository.save(scoreEntity);

        return true;
    }
}
