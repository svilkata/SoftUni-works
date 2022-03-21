package springintro.service;

import springintro.model.entity.Author;
import springintro.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> _06_FindByFirstNameEndsWith(String endsWith);

    List<AuthorNamesWithTotalCopies> _10_getWithTotalCopies();
}
