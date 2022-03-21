package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findBooksByAgeRestrictionEquals(AgeRestriction ageRestr);

    List<Book> findBooksByCopiesBefore(int copies);

    List<Book> findBooksByPriceAfterOrPriceBefore(BigDecimal maxPrice, BigDecimal minPrice);

    List<Book> findBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate beforeDate, LocalDate afterDate);

    List<Book> findBooksByReleaseDateBefore(LocalDate before);

    List<Book> findBooksByTitleContains(String title);

    List<Book> findByAuthorLastNameStartingWith(String startWith);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length")
    int countBooksWithTitleLongerThan(int length);

    int countBooksByAuthorFirstName(String firstName);

    List<Book> findBooksByTitle(String title);



}
