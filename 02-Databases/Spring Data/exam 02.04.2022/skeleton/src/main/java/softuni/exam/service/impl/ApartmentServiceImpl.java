package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportFromXMLOneAppartmentDTO;
import softuni.exam.models.dto.ImportfromXMLApartmentsRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final Path PATH_APARTMENTS = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");
    private final ApartmentRepository apartmentRepository;
    private TownRepository townRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;
    ;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;

        JAXBContext context = JAXBContext.newInstance(ImportfromXMLApartmentsRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return String.join("\n", Files.readAllLines(PATH_APARTMENTS));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ImportfromXMLApartmentsRootDTO appartmentsDTOs =
                (ImportfromXMLApartmentsRootDTO) this.unmarshaller
                        .unmarshal(new FileReader(PATH_APARTMENTS.toAbsolutePath().toString()));

//        appartmentsDTOs.getApartments()
//                .forEach(a -> System.out.printf("%s %s %s%n", a.getApartmentType(), a.getTown(), a.getArea()));

        return appartmentsDTOs
                .getApartments()
                .stream()
                .map(this::importApartment)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Optional<Apartment> findById(long apId) {
        return this.apartmentRepository.findById(apId);
    }

    private String importApartment(ImportFromXMLOneAppartmentDTO dto) {
        if (dto.getArea() < 40.00) {
            return "Invalid apartment";
        }

        Optional<Apartment> optApartment = this.apartmentRepository.findByAreaAndByTown(dto.getArea(), dto.getTown());
        if (optApartment.isPresent()) {
            return "Invalid apartment";
        }

        Apartment apartment = this.modelMapper.map(dto, Apartment.class);
        Optional<Town> town = this.townRepository.findBytownName(dto.getTown());
        apartment.setTown(town.get());

        this.apartmentRepository.save(apartment);

        return "Successfully imported apartment " + dto.getApartmentType() + " - " + dto.getArea();
    }
}
