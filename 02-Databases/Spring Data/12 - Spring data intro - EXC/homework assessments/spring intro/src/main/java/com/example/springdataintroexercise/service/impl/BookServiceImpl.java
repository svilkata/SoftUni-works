package com.example.springdataintroexercise.service.impl;

import com.example.springdataintroexercise.entity.*;
import com.example.springdataintroexercise.repository.BookRepository;
import com.example.springdataintroexercise.service.AuthorService;
import com.example.springdataintroexercise.service.BookService;
import com.example.springdataintroexercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");
                    Book book = getBookFromInfo(bookInfo);
                    bookRepository.save(book);
                });
    }

    @Override
    public List<String> getAllBooksTitleAfter(Integer year) {
        return bookRepository.
                findBooksByReleaseDateAfter(LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore(Integer year) {

        List<String> authorsNames = new ArrayList<>();
        bookRepository
                .findBooksByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(Book::getAuthor)
                .forEach(author -> {
                    authorsNames.add(String.format("%s %s"
                            , author.getFirstName(), author.getLastName()));
                });

        return authorsNames;
    }

    @Override
    public List<String> getAllBooksFromAuthorOrderedByReleaseDateDescAndBookTitleAscending
            (String firstName, String lastName) {

        List<String> bookInfo = new ArrayList<>();
        bookRepository
                .findAllByAuthorOrderByReleaseDateDescTitle(authorService.getAuthor(firstName, lastName))
                .forEach(book -> {
                    bookInfo.add(String.format("%s %s %d"
                            , book.getTitle()
                            , book.getReleaseDate()
                            , book.getCopies()
                    ));
                });

        return bookInfo;
    }

    private Book getBookFromInfo(String[] bookInfo) {
        EditionType editionType =
                EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1],
                DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction =
                AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();


        return new Book(editionType, releaseDate, copies,
                price, ageRestriction, title, author, categories);
    }
}
