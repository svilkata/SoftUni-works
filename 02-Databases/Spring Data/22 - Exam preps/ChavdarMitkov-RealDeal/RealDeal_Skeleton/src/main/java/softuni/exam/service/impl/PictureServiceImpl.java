package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureSeedFromJSONDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    public static final String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";

    private final PictureRepository pictureRepository;
    private CarRepository carRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        PictureSeedFromJSONDto[] picturesSeedDtos = gson.fromJson(this.readPicturesFromFile(), PictureSeedFromJSONDto[].class);

        return Arrays.stream(picturesSeedDtos)
                .map(this::importOnePicture)
                .collect(Collectors.joining("\n"));
    }

    private String importOnePicture(PictureSeedFromJSONDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid picture";
        }

        Optional<Car> optCar = this.carRepository.findById(dto.getCar());
        if (optCar.isEmpty()) {
            return "Invalid picture - this picture can not be assigned to non-existing car in the database";
        }

        Optional<Picture> optPicture = this.pictureRepository
                .findByNameAndCarId(dto.getName(), dto.getCar());
        if (optPicture.isPresent()) {
            return "Invalid picture - this picture is already uploaded/already exists";
        }

        Picture picture = this.modelMapper.map(dto, Picture.class);
        picture.setCar(optCar.get());
        this.pictureRepository.save(picture);

        return "Successfully import picture - " + dto.getName();

    }
}
