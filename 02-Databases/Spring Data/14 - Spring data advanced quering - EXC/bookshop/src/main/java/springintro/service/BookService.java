package springintro.service;

import springintro.model.entity.Book;
import springintro.model.entity.BookSummary;
import springintro.model.entity.EditionType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> _01_findAllTitlesByAgeRestriction(String agerestriction);

    List<String> _02_FindAllTitlesByEditionTypeAndCopies(EditionType type, int copies);

    List<Book> _03_FindAllWithPriceNotBetween(double lowerBound, double upperBound);

    List<Book> _04_findNotReleasedIn(int releaseYear);

    List<Book> _05_findBooksReleasedBefore(String date);

    List<String> _07_findAllTitlesContaining(String search);

    List<Book> _08_authorsLastnameStartsWith(String search);

    int _09_countBooksWithTitleLongerThan(int symbolsOfTitle);

    BookSummary _11_getInformartionForTitle(String title);

    int _12_addCopiesToBooksAfter(String date, int amountCopiesIncrease);

    int _13_DeleteWithCopiesLessThan(int amountCopies);

    int _14_FindCountOfBooksByAuthor(String authorNames);

    int _14_StoredProcedureFindCountOfBooksByAuthor(String authorNames);
}
