package com.example.springdataintroexercise.service.impl;

import com.example.springdataintroexercise.entity.Author;
import com.example.springdataintroexercise.repository.AuthorRepository;
import com.example.springdataintroexercise.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] authorInfo = row.split("\\s+");
                    authorRepository.save(new Author(authorInfo[0], authorInfo[1]));
                });

    }

    @Override
    public Author getRandomAuthor() {
        long authorId = ThreadLocalRandom
                .current().nextLong(1
                        , authorRepository.count() + 1);

        return authorRepository.findById(authorId).orElse(null);

    }

    @Override
    public List<String> getAllAuthorsOrderedByBookCountDesc() {
        List<String> authorInfo = new ArrayList<>();
        authorRepository
                .findAllByBooksSizeDESC()
                .forEach(author -> {
                    authorInfo.add(String.format("%s %s %d"
                            , author.getFirstName()
                            , author.getLastName()
                            , author.getBooks().size()
                    ));
                });
        return authorInfo;
    }

    @Override
    public Author getAuthor(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }
}
