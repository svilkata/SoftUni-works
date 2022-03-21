package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesByAgeRestriction(String ageRestriction);

    List<String> findAllBookTitlesByEditionTypeAndCopies(String editionType, int copies);

    List<String> findAllBookTitlesAndPricesByPriceNotBetween(double lessThanVal, double moreThanVal);

    List<String> findAllBookTitlesByReleaseDateNotInYear(int year);

    List<String> findAllBooksReleasedBefore(String date);

    List<String> findAllBookTitlesContaining(String symbol);

    List<String> findAllBookTitlesAuthorsLastNameStartsWith(String symbol);

    long countAllBooksByTitleLengthGreaterThan(long length);

    List<String> getBookInfoByTitle(String title);

    int updateBookCopiesByReleaseDateAfter(String date, int copies);

    int deleteBooksIfCopiesLessThan(int copies);
}
