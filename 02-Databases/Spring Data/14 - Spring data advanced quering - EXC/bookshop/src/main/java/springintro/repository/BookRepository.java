package springintro.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import springintro.model.entity.AgeRestriction;
import springintro.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springintro.model.entity.BookSummary;
import springintro.model.entity.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    @Query("SELECT b.title FROM Book b WHERE b.ageRestriction = :ageRestriction")
    List<String> findTitleByAgeRestriction(@Param(value = "ageRestriction") AgeRestriction restriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);


    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByTitleContaining(String search);

    List<Book> findByAuthorLastNameStartingWith(String search);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :leng")
    int countBooksWithTitleLongerThan(@Param(value = "leng") int symbolsOfTitle);

    @Query("SELECT b.title AS title, b.editionType AS editionType, b.ageRestriction AS ageRestriction, b.price AS price" +
            " FROM Book b" +
            " WHERE b.title = :title")
    BookSummary findSummaryForTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :amountCopiesIncrease WHERE b.releaseDate > :date")
    int addCopiesToBooksAfter(LocalDate date, int amountCopiesIncrease);

    @Transactional
    int deleteByCopiesLessThan(int amountCopies);

    @Query("SELECT count(b) FROM Book AS b" +
            " JOIN b.author AS a" +
            " WHERE CONCAT(a.firstName, ' ', a.lastName) = :authorNames")
    int countBooksByAuthorName(String authorNames);

    @Query(value = "CALL totalAmountOfBooksByAuthor(:authorNames);", nativeQuery = true)
    int findCountBooksAnAuthorWrote(String authorNames);
}
