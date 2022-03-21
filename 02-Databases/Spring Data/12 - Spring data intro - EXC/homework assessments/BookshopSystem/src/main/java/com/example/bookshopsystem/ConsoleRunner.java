package com.example.bookshopsystem;

import com.example.bookshopsystem.entities.Author;
import com.example.bookshopsystem.entities.Book;
import com.example.bookshopsystem.repositories.AuthorRepository;
import com.example.bookshopsystem.repositories.BookRepository;
import com.example.bookshopsystem.servecies.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void _01_bookAfter2000() {
        LocalDate year2000 = LocalDate.of(2000,12,31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b-> System.out.println(b.getReleaseDate()+" "+ b.getTitle()));
    }

    private void _02_bookAfter2000(){
        LocalDate year1990 = LocalDate.of(1990,1,1);

       List<Author> authors= this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

       authors.forEach(author-> System.out.println(author.getFirstName()+" "+ author.getLastName()));
    }


    private void _03_AllAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors.stream()
                .sorted((l,r)->r.getBooks().size()-l.getBooks().size())
                .forEach(author ->
                        System.out.println(author.getFirstName()+" "+author.getLastName()+" -> "+author.getBooks().size()));

    }

    private void _04_bookFromAuthorOrderedByReleaseDateAndBookTitle() {
        String name = "George Powell";

        List<Book> books = this.bookRepository.findAll();
        books.stream().filter(b->b.getAuthor().getFirstName().equals("George") && b.getAuthor().getLastName().equals("Powell"))
                .forEach(b-> System.out.println(b.getTitle()+" "+b.getReleaseDate()+" "+b.getCopies()));

        //books.forEach(b-> System.out.println(b.getTitle()+" "+b.getReleaseDate()+" "+b.getCopies()));
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start of the Game !");

//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedBooks();

       // this._01_bookAfter2000();
       // this._02_bookAfter2000();
       //this._03_AllAuthorsOrderedByBookCount();
        this._04_bookFromAuthorOrderedByReleaseDateAndBookTitle();
    }


}
