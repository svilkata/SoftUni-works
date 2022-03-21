package com.example._12_spring_data_intro_exercise.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedCategories() throws IOException;

    void seedBooks() throws IOException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }
}
