package com.example.springintro.service.impl;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.projection.AuthorNamesAndCopies;
import com.example.springintro.repository.AuthorRepository;
import com.example.springintro.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<String> getAllAuthorsOrderByCountOfTheirBooks() {
        return authorRepository
                .findAllByBooksSizeDESC()
                .stream()
                .map(author -> String.format("%s %s %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllAuthorsByFirstNameStartingWith(String symbol) {
        return authorRepository.findAllByFirstNameEndingWith(symbol)
                .stream()
                .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllAuthorsWithTotalBookCopies() {
//        Alternate Version: Getting the copies count in the Business Layer.
//        ------------------------------------------------------------------
//        List<Author> authorsList = authorRepository.findAll();
//
//        List<String> authorCopiesList = new ArrayList<>();
//        for (Author author : authorsList) {
//
//            int countCopies = 0;
//            for (Book book : author.getBooks()) {
//                countCopies += book.getCopies();
//            }
//
//            authorCopiesList.add(String.format("%s %s - %d", author.getFirstName(), author.getLastName(), countCopies));
//        }
//
//        authorCopiesList.sort((a, b) -> Integer.compare(Integer.parseInt(b.split("- ")[1]), Integer.parseInt(a.split("- ")[1])));

//        return authorCopiesList;

        return authorRepository.getAllAuthorsWithTotalBookCopies().stream()
                .map(author -> String.format("%s %s - %d", author.getFirstName(), author.getLastName(), author.getCopiesSum()))
                .collect(Collectors.toList());
    }

    @Override
    public int getBooksCountByAuthorName(String firstName, String lastName) {
        return authorRepository.getTotalBooksByAuthor(firstName, lastName);
    }
}
