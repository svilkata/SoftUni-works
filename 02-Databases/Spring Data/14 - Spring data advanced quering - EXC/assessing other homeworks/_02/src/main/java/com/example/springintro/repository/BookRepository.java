package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.model.entity.projection.BookReducedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThanVal, BigDecimal moreThanVal);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate dateBefore, LocalDate dateAfter);

    List<Book> findAllByTitleContaining(String symbol);

    List<Book> findAllByAuthor_LastNameStartingWith(String symbol);

    @Query(value = "SELECT COUNT(*) FROM Book AS b WHERE CHAR_LENGTH(b.title) > :lengthParam")
    long countAllByTitleLengthGreaterThan(@Param(value = "lengthParam")long length);

    @Query(value = "SELECT b.title AS title,  b.editionType AS editionType, b.ageRestriction AS ageRestriction, b.price AS price FROM Book AS b WHERE b.title = :titleParam")
    List<BookReducedInfo> getBookInformationByTitle(@Param(value = "titleParam") String title);

    @Transactional
    @Modifying
    @Query(value = "Update Book AS b SET b.copies = b.copies + :copiesParam WHERE b.releaseDate > :dateParam")
    int updateBookCopiesByReleaseDateAfter(@Param(value = "dateParam") LocalDate afterDate, @Param(value = "copiesParam") int copies);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Book AS b WHERE b.copies < :copiesParam")
    int deleteBookByCopiesLessThan(@Param(value = "copiesParam") int copies);
}
