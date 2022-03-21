package com.example.springdataintroexercise;

import com.example.springdataintroexercise.service.AuthorService;
import com.example.springdataintroexercise.service.BookService;
import com.example.springdataintroexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final Scanner scanner;

    public CommandLineRunnerImpl(CategoryService categoryService
            , AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        userInterface();
    }

    private void userInterface() {
        menu();
        System.out.print("Please chose option: ");
        int option = Integer.parseInt(scanner.nextLine());
        while (!(option == 5)) {
            switch (option) {
                case 1:
                    bookService.getAllBooksTitleAfter(2000)
                            .forEach(System.out::println);
                    break;
                case 2:
                    bookService.getAllAuthorsWithAtLeastOneBookWithReleaseDateBefore(1990)
                            .forEach(System.out::println);
                    break;
                case 3:
                    authorService.getAllAuthorsOrderedByBookCountDesc()
                            .forEach(System.out::println);
                    break;
                case 4:
                    bookService
                            .getAllBooksFromAuthorOrderedByReleaseDateDescAndBookTitleAscending
                                    ("George", "Powell")
                            .forEach(System.out::println);
                    break;
                default:
                    System.out.println("Please chose correct option!");
            }
            menu();
            System.out.print("Please chose option: ");
            option = Integer.parseInt(scanner.nextLine());
        }
    }

    private void menu() {

        System.out.println("===============================Menu==========================================");
        System.out.println("1.Get all books after the year 2000. Print only their titles.");
        System.out.println("2. Get all authors with at least one " +
                "book with release date before 1990. Print their first name and last name.");
        System.out.println("3.Get all authors, ordered by the number of their books " +
                "(descending). Print their first name, last name and book count.");
        System.out.println("4. Get all books from author George Powell, ordered by their release date (descending), " +
                "then by book title (ascending). Print the book's title, release date and copies.");
        System.out.println("5. Exit to program");
        System.out.println("==============================================================================");
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
