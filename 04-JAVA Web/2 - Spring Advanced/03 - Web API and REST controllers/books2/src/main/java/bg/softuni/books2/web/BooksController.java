package bg.softuni.books2.web;

import bg.softuni.books2.model.dto.BookDTO;
import bg.softuni.books2.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")  //разрешавам на всички да четат моето /api/books
@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
        Optional<BookDTO> bookOpt = this.bookService.getBookById(bookId);
        if (bookOpt.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(bookOpt.get());
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity
                .ok(this.bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId){
        this.bookService.deleteById(bookId);
        return ResponseEntity
                .noContent()   //без content в response-a
                .build();
    }

    @PostMapping()
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder builder){
        Long newBookID = this.bookService.createBook(bookDTO);
        URI location = builder.path("/api/books/{id}")
                .buildAndExpand(newBookID)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
