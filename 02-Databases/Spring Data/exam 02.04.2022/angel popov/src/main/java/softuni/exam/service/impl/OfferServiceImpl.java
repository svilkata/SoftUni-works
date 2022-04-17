package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportApartmentDto;
import softuni.exam.models.dto.ImportApartmentRootDto;
import softuni.exam.models.dto.ImportOfferDto;
import softuni.exam.models.dto.ImportOfferRootDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;
    private ApartmentRepository apartmentRepository;
    private AgentRepository agentRepository;

    private ModelMapper modelMapper;

    private Unmarshaller unmarshaller;

    private Validator validator;

    private final String STRING_PATH = "src/main/resources/files/xml/offers.xml";
    private Path path;

    public OfferServiceImpl(OfferRepository offerRepository, ApartmentRepository apartmentRepository, AgentRepository agentRepository) throws JAXBException {
        this.offerRepository = offerRepository;
        this.apartmentRepository = apartmentRepository;
        this.agentRepository = agentRepository;

        this.modelMapper = new ModelMapper();

        this.unmarshaller = JAXBContext.newInstance(ImportOfferRootDto.class).createUnmarshaller();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

        path = Paths.get(STRING_PATH);
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count()>0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportOfferRootDto importOfferRootDto = (ImportOfferRootDto)
                unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));
        return importOfferRootDto.getImportOfferDtoList().stream()
                .map(this::importOffer)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importOffer(ImportOfferDto importOfferDto) {

        Set<ConstraintViolation<ImportOfferDto>> errors = validator.validate(importOfferDto);

        if (!errors.isEmpty()) {
            return "Invalid offer";
        }

        Optional<Agent> optionalAgent = this.agentRepository.findByFirstName(importOfferDto.getAgentNameDto().getName());

        if(!optionalAgent.isPresent()){
            return "Invalid offer";
        }

        Optional<Apartment> optionalApartment = this.apartmentRepository.findById(importOfferDto.getApartmentIdDto().getId());

        Apartment apartment = optionalApartment.get();

        Offer offer = this.modelMapper.map(importOfferDto,Offer.class);

        offer.setApartment(apartment);
        offer.setAgent(optionalAgent.get());


        this.offerRepository.save(offer);

        return String.format("Successfully imported offer %s", offer.getPrice().setScale(2, RoundingMode.HALF_UP).toString());
    }

    @Override
    public String exportOffers() {

        List<Offer> bestOffers = this.offerRepository.findBestOffers();

        return bestOffers.stream()
                .map(o -> {
                    return String.format("Agent %s %s with offer №%d:\n" +
                            "   \t-Apartment area: %.2f\n" +
                            "   \t--Town: %s\n" +
                            "   \t---Price: %s$",o.getAgent().getFirstName(),o.getAgent().getLastName(),o.getId(),
                            o.getApartment().getArea(),
                            o.getApartment().getTown().getTownName(),
                            o.getPrice().setScale(2, RoundingMode.HALF_UP));
                }).collect(Collectors.joining(System.lineSeparator()));
    }
}
