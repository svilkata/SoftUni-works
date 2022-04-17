package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.ApartmentSeedDTO;
import softuni.exam.models.dto.xml.ApartmentSeedRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private static final String APARTMENTS_FILE_PATH = "src/main/resources/files/xml/apartments.xml";
    private static final String INVALID_MESSAGE = "Invalid apartment";

    private final ApartmentRepository apartmentRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ModelMapper modelMapper,
                                ValidationUtil validationUtil, XmlParser xmlParser, TownService townService) {
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(APARTMENTS_FILE_PATH, ApartmentSeedRootDTO.class)
                .getApartments()
                .stream().forEach(apartmentSeedDTO -> {
                    if (validationUtil.isValid(apartmentSeedDTO) &&
                            apartmentRepository.findApartmentByArea(apartmentSeedDTO.getArea()) == null) {
                        Apartment apartment = modelMapper.map(apartmentSeedDTO, Apartment.class);
                        apartment.setTown(townService.FindTownByName(apartmentSeedDTO.getTown()));
                        apartmentRepository.save(apartment);

                        sb.append(String.format("Successfully imported apartment %s - %.2f",
                                        apartmentSeedDTO.getApartmentType(), apartmentSeedDTO.getArea()))
                                .append(System.lineSeparator());
                    } else {
                        sb.append(INVALID_MESSAGE)
                                .append(System.lineSeparator());
                    }
                });
//                .filter(apartmentSeedDTO -> {
//                    boolean isValid = validationUtil.isValid(apartmentSeedDTO);
//                    sb.append(isValid ? String.format("Successfully imported apartment %s - %.2f",
//                                    apartmentSeedDTO.getApartmentType(), apartmentSeedDTO.getArea())
//                                    : "Invalid apartment")
//                            .append(System.lineSeparator());
//                    return isValid;
//                })
//                .map(apartmentSeedDTO -> {
//                    Apartment apartment = modelMapper.map(apartmentSeedDTO, Apartment.class);
//                    apartment.setTown(townService.FindTownByName(apartmentSeedDTO.getTown()));
//                    return apartment;
//                })
//                .forEach(apartmentRepository::save);
        return sb.toString();
    }

    @Override
    public Apartment FindApartmentById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }
}
