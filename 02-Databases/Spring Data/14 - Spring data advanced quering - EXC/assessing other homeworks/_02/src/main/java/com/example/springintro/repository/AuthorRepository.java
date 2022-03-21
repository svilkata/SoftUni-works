package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.projection.AuthorNamesAndCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAllByFirstNameEndingWith(String symbol);

    @Query(value = "SELECT a.firstName AS firstName , a.lastName AS lastName, SUM(b.copies) AS copiesSum FROM Author AS a JOIN a.books AS b GROUP BY a.id ORDER BY SUM(b.copies) DESC")
    List<AuthorNamesAndCopies> getAllAuthorsWithTotalBookCopies();

    @Query(value = "CALL TOTAL_BOOKS_BY_AUTHOR(:firstNameParam, :lastNameParam);", nativeQuery = true)
    int getTotalBooksByAuthor(@Param(value = "firstNameParam") String firstName, @Param(value = "lastNameParam") String lastName);
}
