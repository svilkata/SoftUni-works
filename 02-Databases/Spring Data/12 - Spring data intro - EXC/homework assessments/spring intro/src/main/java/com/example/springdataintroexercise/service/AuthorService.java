package com.example.springdataintroexercise.service;

import com.example.springdataintroexercise.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderedByBookCountDesc();

    Author getAuthor(String firstName, String lastName);
}
