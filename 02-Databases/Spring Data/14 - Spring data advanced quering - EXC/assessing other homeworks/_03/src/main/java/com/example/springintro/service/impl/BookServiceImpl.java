package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
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
    public String findBooksByTitle(String title){
        return bookRepository.findBooksByTitle(title)
                .stream()
                .map(book -> String.format("%s %s %s %.2f", book.getTitle(), book.getEditionType(),
                                                            book.getAgeRestriction(), book.getPrice()))
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public int countBooksByAuthorFirstName(String firstName){
        return bookRepository.countBooksByAuthorFirstName(firstName);
    }

    @Override
    public List<String> findByAuthorLastNameStartingWith(String search) {
        return bookRepository.findByAuthorLastNameStartingWith(search)
                .stream()
                .map(book -> String.format("%s (%s %s)",book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int countBooksWithTitleLongerThan(int count){
        return bookRepository.countBooksWithTitleLongerThan(count);
    }

    @Override
    public List<String> findBooksByTitleContains(String title){
        return bookRepository.findBooksByTitleContains(title)
                .stream()
                .map(book -> String.format("%s", book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByReleaseDateBeforeOrReleaseDateAfter(String year){
        DateTimeFormatter formatBefore = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        LocalDate before = LocalDate.parse(year, formatBefore);

        DateTimeFormatter formatAfter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 12)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 31)
                .toFormatter();

        LocalDate after = LocalDate.parse(year, formatAfter);

        return bookRepository
                .findBooksByReleaseDateBeforeOrReleaseDateAfter(before, after)
                .stream()
                .map(book -> String.format("%s", book.getTitle()))
                .collect(Collectors.toList());

    }

    @Override
    public List<String> findBooksByReleaseDateBefore(String date){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd-MM-yyyy")
                .toFormatter();

        LocalDate currentDate = LocalDate.parse(date, formatter);

        return bookRepository
                .findBooksByReleaseDateBefore(currentDate)
                .stream()
                .map(book -> String.format("%s %s %.2f", book.getTitle(),
                                                         book.getEditionType(),
                                                         book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByPriceAfterAndPriceBefore(double maxPrice, double minPrice){
        BigDecimal currentMinPrice = new BigDecimal(minPrice);
        BigDecimal currentMaxPrice = new BigDecimal(maxPrice);

        return bookRepository
                .findBooksByPriceAfterOrPriceBefore(currentMaxPrice, currentMinPrice)
                .stream()
                .map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByCopiesBefore(int copies){
        return bookRepository
                .findBooksByCopiesBefore(copies)
                .stream()
                .map(book -> String.format("%s", book.getTitle()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByAgeRestrictionEquals(String ageRestriction){
        AgeRestriction currentRestr = AgeRestriction.valueOf(ageRestriction);
        return bookRepository
                .findBooksByAgeRestrictionEquals(currentRestr)
                .stream()
                .map(book -> String.format("%s", book.getTitle()))
                .distinct()
                .collect(Collectors.toList());
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
}
