package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportFromJSONTownDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        Path pathTowns = Path.of("src", "main", "resources", "files", "json", "towns.json");

        return String.join("\n", Files.readAllLines(pathTowns));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        ImportFromJSONTownDTO[] importTownsDTOS = this.gson.fromJson(json, ImportFromJSONTownDTO[].class);

        String result = Arrays.stream(importTownsDTOS)
                .map(this::importTown)
                .collect(Collectors.joining("\n"));

        return result;
    }

    private String importTown(ImportFromJSONTownDTO dto){
        Set<ConstraintViolation<ImportFromJSONTownDTO>> validationErrors = this.validator.validate(dto);

        if (!validationErrors.isEmpty()){
            return "Invalid town";
        }

        Optional<Town> optTown = this.townRepository.findBytownName(dto.getTownName());
        if (optTown.isPresent()) {
            return "Invalid town - town already exists";
        }

        Town town = this.modelMapper.map(dto, Town.class);

        this.townRepository.save(town);
        return "Successfully imported town " + town.getTownName() + " - " + town.getPopulation();
    }
}
