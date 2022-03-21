package com.example.springdataintroexercise.repository;

import com.example.springdataintroexercise.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size")
    List<Author> findAllByBooksSizeDESC();

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
