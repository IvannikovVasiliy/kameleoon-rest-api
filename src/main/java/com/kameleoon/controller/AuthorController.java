package com.kameleoon.controller;

import com.kameleoon.model.Author;
import com.kameleoon.model.AuthorModel;
import com.kameleoon.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorModel> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public String createAuthor(@RequestBody AuthorModel authorModel) {
        authorService.createAuthor(authorModel);

        return "Author CREATED";
    }
}
