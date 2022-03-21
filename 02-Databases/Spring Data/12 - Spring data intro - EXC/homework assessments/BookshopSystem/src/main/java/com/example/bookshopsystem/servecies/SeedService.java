package com.example.bookshopsystem.servecies;

import java.io.IOException;

public interface SeedService {

    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategories() throws IOException;

    default void seedAll() throws IOException {
        seedAuthors();
        seedBooks();
        seedCategories();
    }
}
