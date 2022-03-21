package com.example._12_spring_data_intro_exercise;

import com.example._12_spring_data_intro_exercise.entities.Author;
import com.example._12_spring_data_intro_exercise.entities.Book;
import com.example._12_spring_data_intro_exercise.repositories.AuthorRepository;
import com.example._12_spring_data_intro_exercise.repositories.BookRepository;
import com.example._12_spring_data_intro_exercise.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void _01_booksAfter2000() {
        LocalDate yearAfter = LocalDate.of(2000,1,1);

        List<Book> books =  this.bookRepository.findByReleaseDateAfter(yearAfter);

        books.forEach(b -> System.out.println(b.getReleaseDate() + " " + b.getTitle()));
    }

    private void _02_allAuthorsWithBookBefore1990() {
        LocalDate year = LocalDate.of(1990, 1,1);

        List<Author> authorList = this.authorRepository.findDistinctByBooksReleaseDateBefore(year);

        for (Author author : authorList) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();
        authors.stream().sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a ->System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()));
    }

    private void _04_getBookByAuthor() {
        Author author = authorRepository.findById(4).get();
        List<Book> books = bookRepository.findByAuthorOrderByTitle(author);

        books.sort(Comparator.comparing(Book::getReleaseDate));

        for (Book book : books) {
            System.out.printf("%s - %s - %d%n",book.getTitle(), book.getReleaseDate().toString(), book.getCopies());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedService.seedAll();

        //this._01_booksAfter2000();
        //this._02_allAuthorsWithBookBefore1990();
        //this._03_allAuthorsOrderedByBookCount();
        this._04_getBookByAuthor();
    }




}
