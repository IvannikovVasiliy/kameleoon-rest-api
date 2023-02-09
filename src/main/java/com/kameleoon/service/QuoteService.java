package com.kameleoon.service;


import com.kameleoon.model.*;
import com.kameleoon.repository.AuthorRepository;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.specification.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final AuthorRepository authorRepository;

    public List<QuoteModel> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();

        List<QuoteModel> res = new ArrayList<>();
        for (var q : quotes) {
            AuthorDto authorDto = new AuthorDto();
            authorDto.name = q.getAuthor().getName();
            authorDto.surname = q.getAuthor().getSurname();
            authorDto.pseudonym = q.getAuthor().getPseudonym();

            QuoteModel quoteModel = QuoteModel
                    .builder()
                    .text(q.getText())
                    .author(authorDto)
                    .build();

            res.add(quoteModel);
        }

        return res;
    }

    public boolean createQuote(QuoteModel quoteModel) {
        AuthorSearchCriteria authorSearchCriteria = AuthorSearchCriteria
                .builder()
                .name(quoteModel.getAuthor().name)
                .surname(quoteModel.getAuthor().surname)
                .pseudonym(quoteModel.getAuthor().pseudonym)
                .build();

        Author author = authorRepository.findOne(new AuthorSpecification(authorSearchCriteria)).get();
        Quote quote = new Quote(quoteModel.getText(), author);
        quoteRepository.save(quote);

        return true;
    }
}
