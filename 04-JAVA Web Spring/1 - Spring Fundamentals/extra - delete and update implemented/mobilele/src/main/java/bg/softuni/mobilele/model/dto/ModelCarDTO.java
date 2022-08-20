package bg.softuni.mobilele.model.dto;

public class ModelCarDTO {
    private Long id;
    private String name;


    public ModelCarDTO() {
    }

    public Long getId() {
        return id;
    }

    public ModelCarDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelCarDTO setName(String name) {
        this.name = name;
        return this;
    }
}
