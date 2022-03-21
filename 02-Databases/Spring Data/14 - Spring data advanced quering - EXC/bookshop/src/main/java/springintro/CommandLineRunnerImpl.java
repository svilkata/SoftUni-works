package springintro;

import org.springframework.beans.factory.annotation.Autowired;
import springintro.model.entity.BookSummary;
import springintro.model.entity.EditionType;
import springintro.service.AuthorService;
import springintro.service.BookService;
import springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

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
//        _01_SelectFindAllTitlesByAgerestriction_01();
//        _02_GoldenBooks_02();
//        _03_BooksbyPrice_03();
//        _04_NotReleasedBooks_04();
//        _05_BooksReleasedBeforeDate();
//        _06_AuthorsSearch();
//        _07_BooksSearch();
//        _08_BookTitlesSearch();
//        _09_CountBooks();
//        _10_TotalBookCopies();
//        _11_ReducedBook();
//        _12_IncreaseBookCopies();
//        _13_RemoveBooks();
//        _14_NumberOfBooksByAuthor();
        _14_StoredProcedure();
    }

    private void _14_StoredProcedure() {
        Scanner sc = new Scanner(System.in);
        String authorNames = sc.nextLine();

        int countBooks = this.bookService._14_StoredProcedureFindCountOfBooksByAuthor(authorNames);
        System.out.println(String.format("%s has written %d books", authorNames, countBooks));
    }

    private void _14_NumberOfBooksByAuthor() {
        Scanner sc = new Scanner(System.in);
        String authorNames = sc.nextLine();

        int countBooks = this.bookService._14_FindCountOfBooksByAuthor(authorNames);
        System.out.println(String.format("%s has written %d books", authorNames, countBooks));
    }

    private void _01_SelectFindAllTitlesByAgerestriction_01() {
        Scanner sc = new Scanner(System.in);
        String restriction = sc.nextLine();

        this.bookService._01_findAllTitlesByAgeRestriction(restriction)
                .forEach(System.out::println);
    }

    private void _02_GoldenBooks_02() {
        this.bookService._02_FindAllTitlesByEditionTypeAndCopies(EditionType.GOLD, 5000)
                .forEach(System.out::println);
    }

    private void _03_BooksbyPrice_03() {
        this.bookService._03_FindAllWithPriceNotBetween(5.0, 40.0)
                .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));
    }

    private void _04_NotReleasedBooks_04() {
        Scanner sc = new Scanner(System.in);
        int releaseYear = Integer.parseInt(sc.nextLine());

        this.bookService._04_findNotReleasedIn(releaseYear)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void _05_BooksReleasedBeforeDate() {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();

        this.bookService._05_findBooksReleasedBefore(date)
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getEditionType() + " " + b.getPrice()));
    }

    private void _06_AuthorsSearch() {
        Scanner sc = new Scanner(System.in);
        String endsWith = sc.nextLine();

        this.authorService._06_FindByFirstNameEndsWith(endsWith)
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _07_BooksSearch() {
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();

        this.bookService._07_findAllTitlesContaining(search)
                .forEach(System.out::println);
    }

    private void _08_BookTitlesSearch() {
        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();

        this.bookService._08_authorsLastnameStartsWith(search)
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                        b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    private void _09_CountBooks() {
        Scanner sc = new Scanner(System.in);
        int symbolsOfTitle = Integer.parseInt(sc.nextLine());

        int totalNumberOfBooks = this.bookService._09_countBooksWithTitleLongerThan(symbolsOfTitle);

        System.out.println(String.format("There are %d books with longer title than %d symbols",
                totalNumberOfBooks, symbolsOfTitle));
    }

    private void _10_TotalBookCopies() {
        this.authorService._10_getWithTotalCopies()
                .forEach(a -> System.out.println(a.getFirstName() + " "
                        + a.getLastName() + " - "
                        + a.getTotalCopies()));
    }

    private void _11_ReducedBook() {
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();

        BookSummary bookSummary = this.bookService._11_getInformartionForTitle(title);

        System.out.println(bookSummary.getTitle() + " " + bookSummary.getEditionType() +
                " " + bookSummary.getAgeRestriction() + " " + bookSummary.getPrice());
    }

    private void _12_IncreaseBookCopies() {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        int amountCopiesIncrease = Integer.parseInt(sc.nextLine());

        int booksUpdated = this.bookService._12_addCopiesToBooksAfter(date, amountCopiesIncrease);

        System.out.printf("%d books are released after %s, so total of %d book copies were added",
                booksUpdated,
                date,
                booksUpdated * amountCopiesIncrease);
    }

    private void _13_RemoveBooks() {
        Scanner sc = new Scanner(System.in);
        int amountCopies = Integer.parseInt(sc.nextLine());

        int deletedBooks = this.bookService._13_DeleteWithCopiesLessThan(amountCopies);
        System.out.println(deletedBooks + " books were deleted.");
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
