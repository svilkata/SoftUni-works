package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();


        //printBooksTitlesByAgeRestriction();
        //findBooksByCopiesLessThan5000();
        //findBooksByPriceLowerThan5AndHigherThan40();
        //findBooksByDifferenceYearByY();
        //findBooksBeforeDate();
        //findAuthorsByFirstNameEndingWith();
        //findBooksContainsGivenString();
        //countBooks();
        //bookTitlesSearch();
        //totalBookCopies();
        reducedBook();
        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
        //pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }
        //1.Books Titles by Age Restriction
    private void printBooksTitlesByAgeRestriction(){
        Scanner sc = new Scanner(System.in);
        String ageRestriction = sc.nextLine();
        bookService
                .findBooksByAgeRestrictionEquals(ageRestriction.toUpperCase(Locale.ROOT))
                .forEach(System.out::println);
    }

    //2.Golden Books
    private void findBooksByCopiesLessThan5000(){
        bookService
                .findBooksByCopiesBefore(5000)
                .forEach(System.out::println);
    }

    //3.Books by Price
    private void findBooksByPriceLowerThan5AndHigherThan40(){
        bookService
                .findBooksByPriceAfterAndPriceBefore(40, 5)
                .forEach(System.out::println);
    }

    //4.Not Released Books
    private void findBooksByDifferenceYearByY(){
        Scanner sc = new Scanner(System.in);
        String year = sc.nextLine();
        bookService
                .findBooksByReleaseDateBeforeOrReleaseDateAfter(year)
                .forEach(System.out::println);

    }

    //5.Books Released Before Date
    private void findBooksBeforeDate(){
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        bookService
                .findBooksByReleaseDateBefore(date)
                .forEach(System.out::println);
    }

    //6.Authors Search
    private void findAuthorsByFirstNameEndingWith(){
        Scanner sc = new Scanner(System.in);
        String endingWith = sc.nextLine();

        authorService
                .findAuthorsByFirstNameEndingWith(endingWith)
                .forEach(System.out::println);
    }

    //7.Books Search
    private void findBooksContainsGivenString(){
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        bookService
                .findBooksByTitleContains(title)
                .forEach(System.out::println);

    }

    //8.Book Titles Search
    private void bookTitlesSearch(){
        Scanner sc = new Scanner(System.in);
        String start = sc.nextLine();

        bookService
                .findByAuthorLastNameStartingWith(start)
                .forEach(System.out::println);
    }

    //9.Count Books
    private void countBooks(){
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int countOfTitle = bookService.countBooksWithTitleLongerThan(count);
        System.out.printf("There are %d books with longer title than %d symbols%n", countOfTitle, count);
    }

    //10. Total Book Copies
    private void totalBookCopies(){
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String last = sc.nextLine();

        int countOfBooks = bookService.countBooksByAuthorFirstName(first);
        System.out.printf("%s %s - %d%n", first, last, countOfBooks);

    }

    //11. Reduced Book
    private void reducedBook(){
        Scanner sc = new Scanner(System.in);
        String currentTitle = sc.nextLine();

        System.out.println(bookService.findBooksByTitle(currentTitle));
    }
    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
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
