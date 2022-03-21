package com.example.springintro.service;

import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findBooksByAgeRestrictionEquals(String ageRestriction);

    List<String> findBooksByCopiesBefore(int copies);

    List<String> findBooksByPriceAfterAndPriceBefore(double maxPrice, double minPrice);

    List<String> findBooksByReleaseDateBeforeOrReleaseDateAfter(String year);

    List<String> findBooksByReleaseDateBefore(String date);

    List<String> findBooksByTitleContains(String title);

    int countBooksWithTitleLongerThan(int length);

    List<String> findByAuthorLastNameStartingWith(String search);

    int countBooksByAuthorFirstName(String firstName);

    String findBooksByTitle(String title);
}
