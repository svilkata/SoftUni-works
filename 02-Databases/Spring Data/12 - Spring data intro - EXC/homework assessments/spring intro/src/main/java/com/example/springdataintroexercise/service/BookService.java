package com.example.springdataintroexercise.service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> getAllBooksTitleAfter(Integer year);

    List<String> getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore(Integer year);

    List<String> getAllBooksFromAuthorOrderedByReleaseDateDescAndBookTitleAscending(String firstName, String lastName);
}
