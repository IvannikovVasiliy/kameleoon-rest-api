package com.kameleoon.service;


import com.kameleoon.model.*;
import com.kameleoon.repository.UserRepository;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public List<QuoteModel> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();

        List<QuoteModel> res = new ArrayList<>();
        for (var q : quotes) {
            UserDto userDto = new UserDto();
            userDto.email = q.getUser().getEmail();
            userDto.login = q.getUser().getLogin();

            QuoteModel quoteModel = QuoteModel
                    .builder()
                    .text(q.getContent())
                    .author(userDto)
                    .build();

            res.add(quoteModel);
        }

        return res;
    }

    public boolean createQuote(QuoteModel quoteModel) {
        UserSearchCriteria userSearchCriteria = UserSearchCriteria
                .builder()
                .email(quoteModel.getAuthor().email)
                .login(quoteModel.getAuthor().login)
                .build();

        User user = userRepository.findOne(new UserSpecification(userSearchCriteria)).get();
        Quote quote = new Quote(quoteModel.getText(), user);
        quoteRepository.save(quote);

        return true;
    }
}
