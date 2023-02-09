package com.kameleoon.service;

import com.kameleoon.model.Author;
import com.kameleoon.model.AuthorModel;
import com.kameleoon.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
//transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public boolean createAuthor(AuthorModel authorModel) {
        Author author = new Author(authorModel.getSurname(), authorModel.getName(), authorModel.getPseudonym());
        authorRepository.save(author);

        return true;
    }

    public List<AuthorModel> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        List<AuthorModel> res = new ArrayList<>();
        for (var author : authors) {
            AuthorModel authorModel = AuthorModel
                    .builder()
                    .name(author.getName())
                    .surname(author.getSurname())
                    .pseudonym(author.getPseudonym())
                    .quotes(author.getQuotes()
                            .stream()
                            .map(quote -> quote.getText())
                            .toList())
                    .build();

            res.add(authorModel);
        }

        return res;
    }
}
