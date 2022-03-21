package com.example.bookshopsystem.servecies;

import com.example.bookshopsystem.entities.*;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "src\\main\\resources\\files\\";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "categories.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "books.txt";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void seedAuthors() throws IOException {
        //Path path = Path.of("").toAbsolutePath();

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(line -> !line.isBlank())
                .map(line -> line.split(" "))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(line -> !line.isBlank())
                .map(this::getBookObject)
                .forEach(bookRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(line -> !line.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    private Book getBookObject(String line) {
        String[] bookParts = line.split("\\s+");
        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];
        LocalDate publishedDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookParts[4])];
        String title = Arrays.stream(bookParts)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title,editionType,price,copies,publishedDate,ageRestriction,author,categories);
    }
}
