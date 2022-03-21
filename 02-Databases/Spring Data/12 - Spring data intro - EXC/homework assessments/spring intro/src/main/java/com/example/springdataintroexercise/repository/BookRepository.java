package com.example.springdataintroexercise.repository;

import com.example.springdataintroexercise.entity.Author;
import com.example.springdataintroexercise.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findBooksByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findAllByAuthorOrderByReleaseDateDescTitle(Author author);
}
