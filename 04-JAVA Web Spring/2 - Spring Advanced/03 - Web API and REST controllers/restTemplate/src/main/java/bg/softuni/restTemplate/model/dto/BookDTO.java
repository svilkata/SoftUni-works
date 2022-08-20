package bg.softuni.restTemplate.model.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private AuthorDTO authorDTO;

    public BookDTO() {
    }

    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public BookDTO setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
        return this;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authorDTO=" + authorDTO +
                '}';
    }
}
