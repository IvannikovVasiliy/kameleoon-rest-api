package com.kameleoon.model;

public class AuthorDto {
    public String name;
    public String surname;
    public String pseudonym;

    public static AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.name = author.getName();
        authorDto.surname = author.getSurname();
        authorDto.pseudonym = author.getPseudonym();
        return authorDto;
    }

    public Author toAuthor() {
        return new Author(name, surname, pseudonym);
    }
}
