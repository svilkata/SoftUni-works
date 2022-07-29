package bg.softuni.books.seedBooks;

import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BooksSeed implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BooksSeed(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0 && authorRepository.count() == 0) {
            initNikolayHaitov();
            initDimitarTalev();
            initElinPelin();
            initVazov();
            initJovkov();
        }
    }

    private void initNikolayHaitov() {
        initAuthor("Николай Хайтов", "Диви разкази");
    }

    private void initDimitarTalev() {
        initAuthor("Димитър Талев", "Тютюн");
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин", "Пижо и Пенда", "Ян Бибиян на луната", "Под манастирската лоза");
    }

    private void initVazov() {
        initAuthor("Иван Вазов", "Препорец и Гусла", "Под Игото", "Тъгите на България");
    }


    private void initJovkov() {
        initAuthor("Йордан Йовков", "Старопланински легенди", "Чифликът край границата");
    }

    private void initAuthor(String authorName, String... books) {
        AuthorEntity author = new AuthorEntity();
        author.setName(authorName);

        List<BookEntity> allBooks = new ArrayList<>();

        for (String bookTitle : books) {
            BookEntity aBook = new BookEntity();
            aBook.setAuthor(author).setTitle(bookTitle).setIsbn(UUID.randomUUID().toString());
            allBooks.add(aBook);
        }

        author.setBooks(allBooks);

        bookRepository.saveAll(allBooks);
    }
}
