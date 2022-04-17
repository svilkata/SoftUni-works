package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ForecastOneFromXmlDto;
import softuni.exam.models.dto.xml.ForecastRootFromXMLDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final String FORECASTS_PATHSTRING_XML = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final CityService cityService;

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityService cityService, ModelMapper modelMapper,
                               ValidationUtil validationUtil, XmlParser xmlParser) {
        this.forecastRepository = forecastRepository;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_PATHSTRING_XML));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ForecastRootFromXMLDto forecastsDtos = xmlParser.fromFile(FORECASTS_PATHSTRING_XML,
                ForecastRootFromXMLDto.class);

        return forecastsDtos.getForecasts()
                .stream()
                .map(this::importOneForecast)
                .collect(Collectors.joining("\n"));
    }

    private String importOneForecast(ForecastOneFromXmlDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid forecast";
        }

        if (dto.getMaxTemperature() < -20.00 || dto.getMaxTemperature() > 60.00 ||
        dto.getMinTemperature() < -50.00 || dto.getMinTemperature() > 40.00) {
            return "Invalid forecast";
        }

        Optional<Forecast> optForecast = this.forecastRepository.findByDayOfWeekAndCityId(
                dto.getDayOfWeek(), dto.getCityId());
        if (optForecast.isPresent()) {
            return "Invalid forecast - forecast for the same weekDay and city already exists";
        }


        Forecast forecast = this.modelMapper.map(dto, Forecast.class);
        Optional<City> optCity = this.cityService.findCityById(dto.getCityId());
        forecast.setCity(optCity.get());
        this.forecastRepository.save(forecast);

        return String.format("Successfully import forecast %s - %.2f",
                dto.getDayOfWeek().toString(), dto.getMaxTemperature());
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();

        this.forecastRepository.extractSundayForecastFromCitiesLessThan150000()
                .forEach(f ->
                        sb.append(String.format("City: %s:\n" +
                                                "\t-min temperature: %.2f\n" +
                                                "\t--max temperature: %.2f\n" +
                                                "\t---sunrise: %s\n" +
                                                "\t----sunset: %s\n",
                                f.getCity().getCityName(), f.getMinTemperature(), f.getMaxTemperature(),
                                f.getSunrise().toString(), f.getSunset().toString()

                                       ))
//                                .append(System.lineSeparator())
                );

        return sb.toString();
    }
}
