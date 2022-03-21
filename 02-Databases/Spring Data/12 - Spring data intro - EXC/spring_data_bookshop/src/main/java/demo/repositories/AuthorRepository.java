package demo.repositories;

import demo.entities.Author;
import demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    Author findByFirstNameAndLastName(String firstName, String lastName);
//    List<Author> findAll();
}
