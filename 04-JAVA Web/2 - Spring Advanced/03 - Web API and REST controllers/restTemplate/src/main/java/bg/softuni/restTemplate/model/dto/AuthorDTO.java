package bg.softuni.restTemplate.model.dto;

public class AuthorDTO {
    private String name;

    public AuthorDTO() {
    }

    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
