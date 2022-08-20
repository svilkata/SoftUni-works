package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    private BookServiceImpl bookService;

    public BooksController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    //called on http://localhost:8080/books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.bookService.getAllBooks();

        return ResponseEntity.ok(allBooks);  //with body allBooks
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        Optional<BookDTO> bookOpt = this.bookService.getByBookId(id);
        if (bookOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bookOpt.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long id) {
        bookService
                .deleteBook(id);

        return ResponseEntity.noContent().build();
    }


    //called on http://localhost:8080/books
//    @PutMapping("")
    @PostMapping("")    //В случая работи за добавяне на нова книга, и за промяна на съществуваща книга (bookRepository.save() или добавя нова книга или презаписва)
    public ResponseEntity<BookDTO> create(
            @RequestBody BookDTO bookDTO,   //сериализирай този JSON и го пъхни в bookDTO == десериализация на body-то до Java обект
            UriComponentsBuilder builder
    ) {
        //http://localhost:8080/books/id
        long bookId = bookService.createBook(bookDTO);
        URI location = builder.path("/books/{id}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //called on http://localhost:8080/books
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(
            @PathVariable("id") long bookId,
            @RequestBody BookDTO bookDTO){
        //todo
        throw new UnsupportedOperationException("coming soon");
    }

}






