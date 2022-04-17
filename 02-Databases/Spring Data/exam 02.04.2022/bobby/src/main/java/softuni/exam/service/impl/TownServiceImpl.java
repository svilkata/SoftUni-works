package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.TownSeedDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson,
                           ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readTownsFileContent(), TownSeedDTO[].class))
                .filter(townSeedDTO -> {
                    boolean isValid = validationUtil.isValid(townSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Town %s - %d",
                                    townSeedDTO.getTownName(), townSeedDTO.getPopulation())
                                    : "Invalid Town")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(townSeedDTO -> modelMapper.map(townSeedDTO, Town.class))
                .forEach(townRepository::save);
        return sb.toString();
    }

    @Override
    public Town FindTownByName(String name) {
        return townRepository.findTownByTownName(name);
    }
}
