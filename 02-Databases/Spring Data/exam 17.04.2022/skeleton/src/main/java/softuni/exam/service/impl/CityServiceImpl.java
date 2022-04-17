package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CityRootFromJsonDto;
import softuni.exam.models.dto.json.CountryRootFromJsonDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private final Path CITIES_PATH = Path.of("src", "main", "resources", "files", "json", "cities.json");
    private final CountryService countryService;
    private final CityRepository cityRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CityServiceImpl(CountryService countryService, CityRepository cityRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryService = countryService;
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(CITIES_PATH));
    }

    @Override
    public String importCities() throws IOException {
        CityRootFromJsonDto[] citiesSeedDtos = gson.fromJson(this.readCitiesFileContent(), CityRootFromJsonDto[].class);

        return Arrays.stream(citiesSeedDtos)
                .map(this::importOneCity)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Optional<City> findCityById(long cityId) {
        return this.cityRepository.findById(cityId);
    }

    private String importOneCity(CityRootFromJsonDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid city";
        }

        Optional<City> optCity = this.cityRepository.findByCityName(dto.getCityName());
        if (optCity.isPresent()) {
            return "Invalid city - city name altready exists";
        }

        City city = this.modelMapper.map(dto, City.class);
        Optional<Country> optCountry = this.countryService.findCountryById(dto.getCountry());
        city.setCountry(optCountry.get());
        this.cityRepository.save(city);

        return "Successfully imported city " + dto.getCityName() + " - " + dto.getPopulation();
    }
}
