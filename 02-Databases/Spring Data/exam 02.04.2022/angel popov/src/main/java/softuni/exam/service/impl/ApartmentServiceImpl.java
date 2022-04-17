package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAgentDto;
import softuni.exam.models.dto.ImportApartmentRootDto;
import softuni.exam.models.dto.ImportApartmentDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository apartmentRepository;

    private TownRepository townRepository;

    private Unmarshaller unmarshaller;

    private ModelMapper modelMapper;

    private Validator validator;

    private final String STRING_PATH = "src/main/resources/files/xml/apartments.xml";
    private Path path;


    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;

        this.unmarshaller = JAXBContext.newInstance(ImportApartmentRootDto.class).createUnmarshaller();

        this.modelMapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        this.path = Paths.get(STRING_PATH);
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {

        ImportApartmentRootDto importApartmentRootDto = (ImportApartmentRootDto)
                unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));
        return importApartmentRootDto.getApartmentDtoList().stream()
                .map(this::importApartment)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importApartment(ImportApartmentDto importApartmentDto) {


        Set<ConstraintViolation<ImportApartmentDto>> errors = validator.validate(importApartmentDto);

        if (!errors.isEmpty()) {
            return "Invalid apartment";
        }

        Town town = this.townRepository.findByTownName(importApartmentDto.getTown()).get();

        Optional<Apartment> optionalApartment = this.apartmentRepository.findByTownIdAndArea(town.getId(), importApartmentDto.getArea());

        if (optionalApartment.isPresent()) {
            return "Invalid apartment";
        }

        Apartment apartment = this.modelMapper.map(importApartmentDto, Apartment.class);

        apartment.setTown(town);

        this.apartmentRepository.save(apartment);

        return String.format("Successfully imported apartment %s - %.2f", apartment.getApartmentType(), apartment.getArea());
    }
}
