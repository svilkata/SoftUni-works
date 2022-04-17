package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedFromJSONDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private static final String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";
    private final CarRepository carRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;

        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        CarSeedFromJSONDto[] carSeedDtos = gson.fromJson(this.readCarsFileContent(), CarSeedFromJSONDto[].class);

        return Arrays.stream(carSeedDtos)
                .map(this::importOneCar)
                .collect(Collectors.joining("\n"));
    }

    private String importOneCar(CarSeedFromJSONDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid car";
        }

        Optional<Car> optCar = this.carRepository.findByMakeAndModelAndKilometers(dto.getMake(), dto.getModel(), dto.getKilometers());
        if (optCar.isPresent()) {
            return "Invalid car - there is already such a car";
        }

        Car car = this.modelMapper.map(dto, Car.class);

        this.carRepository.save(car);
        return "Successfully imported car - " + car.getMake() + " - " + car.getModel();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        this.carRepository.findCarsOrderByPicturesCountDescThenByMakeAsc()
                .forEach(car ->
                        sb.append(String.format("\"Car make - %s, model - %s\n" +
                                                "\tKilometers - %d\n" +
                                                "\tRegistered on - %s\n" +
                                                "\tNumber of pictures - %d\n" +
                                                ". . . \"\n", car.getMake(), car.getModel(), car.getKilometers(),
                                        car.getRegisteredOn(), car.getPictures().size()))
                                .append(System.lineSeparator())

                );


        return sb.toString();
    }
}
