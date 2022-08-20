package bg.softuni.books2.service;

import bg.softuni.books2.model.dto.AuthorDTO;
import bg.softuni.books2.model.dto.BookDTO;
import bg.softuni.books2.model.entity.BookEntity;
import bg.softuni.books2.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<BookDTO> getBookById(Long bookId) {
        return this.bookRepository.findById(bookId)
                .map(this::fromEntityToDTO);
    }

    public List<BookDTO> getAllBooks(){
        return this.bookRepository
                .findAll()
                .stream()
                .map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    private BookDTO fromEntityToDTO(BookEntity bookEntity){
        return new BookDTO()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthorDTO(new AuthorDTO().setName(bookEntity.getAuthor().getName()));
    }

    public void deleteById(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    public Long createBook(BookDTO bookDTO){
        //todo


        return 50L;
    }
}
