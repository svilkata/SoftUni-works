package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();

//        _01_PrintBookTitlesByAgeRestriction("miNor");

//        _02_GoldenBooks("Gold", 5000);

//        _03_BooksByPrice(5, 40);

//        _04_NotReleasedBooks(1998);

//        _05_BooksReleasedBeforeDate("12-04-1992");

//        _06_AuthorsSearchByFirstNameEndingWith("e");

//        _07_BooksSearchTitleContaining("sK");

//        _08_BookTitleSearch("Ric");

//        _09_CountBooksTitleLongerThan(12);

//        _10_TotalBookCopies();

//        _11_ReduceBookInformation("Things Fall Apart");

//        _12_IncreaseBookCopies("12 Oct 2005", 100);

//        _13_RemoveBooks(300);

//        _14_StoredProcedure("Roger", "Porter");

    }

    private void _14_StoredProcedure(String firstName, String lastName) {
        int booksWritten = authorService.getBooksCountByAuthorName(firstName, lastName);
        System.out.println(booksWritten);
    }

    private void _13_RemoveBooks(int copies) {
        int affectedRows = bookService.deleteBooksIfCopiesLessThan(copies);
        System.out.println(affectedRows);
    }

    private void _12_IncreaseBookCopies(String date, int copies) {
        int affectedRows = bookService.updateBookCopiesByReleaseDateAfter(date, copies);

        int copiesAdded = affectedRows * copies;
        System.out.println(copiesAdded);
    }

    private void _11_ReduceBookInformation(String title) {
        bookService.getBookInfoByTitle(title)
                .forEach(System.out::println);
    }

    private void _10_TotalBookCopies() {
        authorService.getAllAuthorsWithTotalBookCopies()
                .forEach(System.out::println);
    }

    private void _09_CountBooksTitleLongerThan(long length) {
        long countBooks = bookService.countAllBooksByTitleLengthGreaterThan(length);
        System.out.println(countBooks);
    }

    private void _08_BookTitleSearch(String symbol) {
        bookService.findAllBookTitlesAuthorsLastNameStartsWith(symbol)
        .forEach(System.out::println);
    }

    private void _07_BooksSearchTitleContaining(String symbol) {
        bookService.findAllBookTitlesContaining(symbol)
                .forEach(System.out::println);
    }

    private void _06_AuthorsSearchByFirstNameEndingWith(String symbol) {
        authorService.getAllAuthorsByFirstNameStartingWith(symbol)
                .forEach(System.out::println);
    }

    private void _05_BooksReleasedBeforeDate(String date) {
        bookService.findAllBooksReleasedBefore(date)
                .forEach(System.out::println);
    }

    private void _04_NotReleasedBooks(int year) {
        bookService.findAllBookTitlesByReleaseDateNotInYear(year)
                .forEach(System.out::println);
    }

    private void _03_BooksByPrice(double lessThanVal, double moreThanVal) {
        bookService.findAllBookTitlesAndPricesByPriceNotBetween(lessThanVal,moreThanVal)
                .forEach(System.out::println);
    }

    private void _02_GoldenBooks(String editionType, int copies) {
        bookService.findAllBookTitlesByEditionTypeAndCopies(editionType, copies)
                .forEach(System.out::println);
    }

    private void _01_PrintBookTitlesByAgeRestriction(String ageRestriction) {
        bookService.findAllBookTitlesByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
