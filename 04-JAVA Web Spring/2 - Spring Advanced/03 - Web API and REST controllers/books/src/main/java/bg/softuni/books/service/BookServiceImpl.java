package bg.softuni.books.service;

import bg.softuni.books.model.dto.AuthorDTO;
import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {
    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    private AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }


    public List<BookDTO> getAllBooks(){
        return bookRepository
                .findAll()
                .stream()
                .map(this::asBook)
                .collect(Collectors.toList());
    }

    private BookDTO asBook(BookEntity book){
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);

        bookDTO.setAuthor(authorDTO);

        return bookDTO;
    }

    public Optional<BookDTO> getByBookId(long id){
        return bookRepository
                .findById(id)
                .map(this::asBook);
    }

    public void deleteBook(long id){
        this.bookRepository.deleteById(id);
    }

    public long createBook(BookDTO bookDTO){
        Optional<AuthorEntity> authorOpt = authorRepository
                .findByName(bookDTO.getAuthor().getName());

        AuthorEntity author;
        if (authorOpt.isEmpty()) {
            author = new AuthorEntity().setName(bookDTO.getAuthor().getName());
        } else {
            author = authorOpt.get();
        }

        BookEntity newBook = new BookEntity()
                .setAuthor(author)
                .setIsbn(bookDTO.getIsbn())
                .setTitle(bookDTO.getTitle());


        return this.bookRepository.save(newBook).getId();
    }
}
