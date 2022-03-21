package demo.services;

import demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorService {
    Author getRandomAuthor();
}
