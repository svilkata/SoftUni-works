package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.BrandCarDTO;
import bg.softuni.mobilele.model.dto.ModelCarDTO;
import bg.softuni.mobilele.model.entity.BrandEntity;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandCarDTO> getBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(be -> mapBrand(be))
                .collect(Collectors.toList());
    }

    private BrandCarDTO mapBrand(BrandEntity brandEntity) {
        List<ModelCarDTO> models = brandEntity
                .getModels()
                .stream()
                .map(model -> mapModel(model))
                .collect(Collectors.toList());

        return new BrandCarDTO()
                .setModels(models)
                .setName(brandEntity.getName());
    }

    private ModelCarDTO mapModel(ModelEntity modelEntity){
        return new ModelCarDTO()
                .setId(modelEntity.getId())
                .setName(modelEntity.getName());
    }
}
