package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTownDto;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private ModelMapper modelMapper;


    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson =new GsonBuilder().create();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        String stringPath = "src/main/resources/files/json/towns.json";
        Path path = Paths.get(stringPath);
        return Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        ImportTownDto[] importTownDtos = this.gson.fromJson(json, ImportTownDto[].class);

        StringBuilder output = new StringBuilder();

        Arrays.stream(importTownDtos)
                .map(this::importTown)
                .collect(Collectors.joining(System.lineSeparator()));


        return output.toString();

    }

    private String importTown(ImportTownDto importTownDto) {
        Set<ConstraintViolation<ImportTownDto>> errors = validator.validate(importTownDto);

        if(!errors.isEmpty()){
            return "Invalid town";
        }

        Optional<Town> optTown = this.townRepository.findByTownName(importTownDto.getTownName());

        if(optTown.isPresent()){
            return "Invalid town";
        }

        Town town = this.modelMapper.map(importTownDto,Town.class);

        this.townRepository.save(town);

       return String.format("Successfully imported town %s - %d",town.getTownName(),town.getPopulation());
    }
}
