package springintro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springintro.model.entity.*;
import springintro.repository.BookRepository;
import springintro.service.AuthorService;
import springintro.service.BookService;
import springintro.service.CategoryService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
    public List<String> _01_findAllTitlesByAgeRestriction(String agerestriction) {
        AgeRestriction restriction = AgeRestriction.valueOf(agerestriction.toUpperCase());

        return this.bookRepository.findTitleByAgeRestriction(restriction);
    }

    @Override
    public List<String> _02_FindAllTitlesByEditionTypeAndCopies(EditionType type, int copies) {
        return this.bookRepository.findByEditionTypeAndCopiesLessThan(type, copies)
                .stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> _03_FindAllWithPriceNotBetween(double lowerBound, double upperBound) {

        return this.bookRepository.findByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(lowerBound), BigDecimal.valueOf(upperBound));
    }

    @Override
    public List<Book> _04_findNotReleasedIn(int releaseYear) {
        LocalDate before = LocalDate.of(releaseYear, 1, 1);
        LocalDate after = LocalDate.of(releaseYear, 12, 31);

        return  this.bookRepository.findByReleaseDateBeforeOrReleaseDateAfter(before, after);

    }

    @Override
    public List<Book> _05_findBooksReleasedBefore(String date) {
//        int[] partsOfDate = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
//        LocalDate before = LocalDate.of(partsOfDate[2], partsOfDate[1], partsOfDate[0]);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate before = LocalDate.parse(date, formatter);

        return this.bookRepository.findAllByReleaseDateBefore(before);
    }

    @Override
    public List<String> _07_findAllTitlesContaining(String search) {

        return  this.bookRepository.findByTitleContaining(search)
                .stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> _08_authorsLastnameStartsWith(String search) {

        return this.bookRepository.findByAuthorLastNameStartingWith(search);
    }

    @Override
    public int _09_countBooksWithTitleLongerThan(int symbolsOfTitle) {

        return this.bookRepository.countBooksWithTitleLongerThan(symbolsOfTitle);
    }

    @Override
    public BookSummary _11_getInformartionForTitle(String title) {
        return this.bookRepository.findSummaryForTitle(title);
    }

    @Override
    public int _12_addCopiesToBooksAfter(String date, int amountCopiesIncrease) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US);
        LocalDate after = LocalDate.parse(date, formatter);

        return this.bookRepository.addCopiesToBooksAfter(after, amountCopiesIncrease);
    }

    @Override
    public int _13_DeleteWithCopiesLessThan(int amountCopies) {
        return this.bookRepository.deleteByCopiesLessThan(amountCopies);
    }

    @Override
    public int _14_FindCountOfBooksByAuthor(String authorNames) {
        return this.bookRepository.countBooksByAuthorName(authorNames);

    }

    @Override
    public int _14_StoredProcedureFindCountOfBooksByAuthor(String authorNames) {
        return this.bookRepository.findCountBooksAnAuthorWrote(authorNames);

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
}
