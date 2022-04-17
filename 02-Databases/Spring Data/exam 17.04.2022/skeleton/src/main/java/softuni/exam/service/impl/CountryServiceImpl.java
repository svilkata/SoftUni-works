package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.CountryRootFromJsonDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(COUNTRIES_FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {
        CountryRootFromJsonDto[] countriesSeedDtos = gson.fromJson(this.readCountriesFromFile(), CountryRootFromJsonDto[].class);

        return Arrays.stream(countriesSeedDtos)
                .map(this::importOneCountry)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Optional<Country> findCountryById(long countryId) {
         return this.countryRepository.findById(countryId);
    }

    private String importOneCountry(CountryRootFromJsonDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid country";
        }

        Optional<Country> optCountry = this.countryRepository.findByCountryName(dto.getCountryName());
        if (optCountry.isPresent()){
            return "Invalid country - country altready exists";
        }

        Country country = this.modelMapper.map(dto, Country.class);
        this.countryRepository.save(country);

        return "Successfully imported country " +
                dto.getCountryName() + " - " + dto.getCurrency();
    }
}
