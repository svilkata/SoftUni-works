package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }

    @Override
    public List<String> findAllBookTitlesByAgeRestriction(String ageRestriction) {
        AgeRestriction enumAgeRestrict = AgeRestriction.valueOf(ageRestriction.toUpperCase());

        return bookRepository.findAllByAgeRestriction(enumAgeRestrict)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesByEditionTypeAndCopies(String editionType, int copies) {
        EditionType enumEditionType = EditionType.valueOf(editionType.toUpperCase());

        return bookRepository.findAllByEditionTypeAndCopiesLessThan(enumEditionType, copies)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesAndPricesByPriceNotBetween(double lowerRange, double upperRange) {
        BigDecimal lowerBound = BigDecimal.valueOf(lowerRange);
        BigDecimal upperBound = BigDecimal.valueOf(upperRange);

        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerBound, upperBound)
                .stream()
                .map(b -> String.format("%s - $%s", b.getTitle(), b.getPrice().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesByReleaseDateNotInYear(int year) {
        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate.of(year, 01, 01), LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksReleasedBefore(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return bookRepository.findAllByReleaseDateBefore(parsedDate)
                .stream()
                .map(b -> String.format("%s %s %s", b.getTitle(), b.getEditionType().toString(), b.getPrice().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesContaining(String symbol) {
        return bookRepository.findAllByTitleContaining(symbol)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesAuthorsLastNameStartsWith(String symbol) {
        return bookRepository.findAllByAuthor_LastNameStartingWith(symbol)
                .stream()
                .map(b -> String.format("%s (%s %s)", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public long countAllBooksByTitleLengthGreaterThan(long length) {
        return bookRepository.countAllByTitleLengthGreaterThan(length);
    }

    @Override
    public List<String> getBookInfoByTitle(String title) {
        return bookRepository.getBookInformationByTitle(title)
                .stream()
                .map(b -> String.format("%s %s %s %.2f", b.getTitle(),
                        b.getEditionType().name(),
                        b.getAgeRestriction().name(),
                        b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public int updateBookCopiesByReleaseDateAfter(String date, int copies) {

        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));

        return bookRepository.updateBookCopiesByReleaseDateAfter(parsedDate, copies);
    }

    @Override
    public int deleteBooksIfCopiesLessThan(int copies) {
        return bookRepository.deleteBookByCopiesLessThan(copies);
    }
}
