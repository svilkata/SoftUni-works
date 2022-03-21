package demo;

import demo.entities.Author;
import demo.entities.Book;
import demo.repositories.AuthorRepository;
import demo.repositories.BookRepository;
import demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppRunner implements ApplicationRunner {
    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public AppRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Starting");
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedAll();

//        this._01_booksAfter2000();
//        this._02_allAuthorsWithBookBefore1990();
//        this._03_allAuthorsOrderedByBookCount();
        this._04_GetAllBooksByAuthorname();
    }

    private void _01_booksAfter2000() {
        LocalDate yearAfter = LocalDate.of(2000, 1, 1);
        List<Book> books = bookRepository.findByReleaseDateAfter(yearAfter);

        books.forEach(b -> System.out.println(b.getReleaseDate() + " " + b.getTitle()));
        System.out.println("Total count result: " + bookRepository.countByReleaseDateAfter(yearAfter));
    }

    private void _02_allAuthorsWithBookBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();
        authors.stream()
                .sorted((f, s) -> Integer.compare(s.getBooks().size(), f.getBooks().size()))
                .forEach(a -> System.out.printf("%s %s -> %d%n", a.getFirstName(),
                        a.getLastName(), a.getBooks().size()));
    }

    private void _04_GetAllBooksByAuthorname() {
        String[] nameOfAuthor = "George Powell".split("\\s+");
        Author author = this.authorRepository.findByFirstNameAndLastName(nameOfAuthor[0], nameOfAuthor[1]);
        List<Book> allBooksByAuthor = this.bookRepository.findAllByAuthorId(author.getId());
        allBooksByAuthor.stream()
                .sorted((f, s) -> {
                    int i = s.getReleaseDate().compareTo(f.getReleaseDate());
                    if (i == 0) {
                        i = f.getTitle().compareTo(s.getTitle());
                    }

                    return i;
                })
                .forEach(book -> System.out.printf("Book: %s, Release Date: %s%n", book.getTitle(), book.getReleaseDate()));
    }
}
