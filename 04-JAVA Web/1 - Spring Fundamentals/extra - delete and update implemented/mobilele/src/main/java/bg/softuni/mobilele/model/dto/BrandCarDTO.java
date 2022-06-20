package bg.softuni.mobilele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class BrandCarDTO {
    private String name;
    private List<ModelCarDTO> models;

    public BrandCarDTO() {
    }

    public List<ModelCarDTO> getModels() {
        return models;
    }

    public BrandCarDTO setModels(List<ModelCarDTO> models) {
        this.models = models;
        return this;
    }

    public BrandCarDTO addModelCar(ModelCarDTO model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }

        this.models.add(model);
        return this;
    }


    public String getName() {
        return name;
    }

    public BrandCarDTO setName(String name) {
        this.name = name;
        return this;
    }
}
