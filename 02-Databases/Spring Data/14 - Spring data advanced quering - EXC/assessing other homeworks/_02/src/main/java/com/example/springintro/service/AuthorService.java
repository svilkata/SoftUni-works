package com.example.springintro.service;

import com.example.springintro.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<String> getAllAuthorsByFirstNameStartingWith(String symbol);

    List<String> getAllAuthorsWithTotalBookCopies();

    int getBooksCountByAuthorName(String firstName, String lastName);
}
