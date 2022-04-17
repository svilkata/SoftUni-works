package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportFromXMLOneOfferDTO;
import softuni.exam.models.dto.ImportfromXMLOffersRootDTO;
import softuni.exam.models.dto.OutputOneOfferTextDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final Path PATH_OFFERS = Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    private final ApartmentService apartmentService;
    private final AgentService agentService;
    private final OfferRepository offerRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(ApartmentService apartmentService, AgentService agentService, OfferRepository offerRepository) throws JAXBException {
        this.apartmentService = apartmentService;
        this.agentService = agentService;
        this.offerRepository = offerRepository;
        JAXBContext context = JAXBContext.newInstance(ImportfromXMLOffersRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(PATH_OFFERS));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportfromXMLOffersRootDTO offersDTOs =
                (ImportfromXMLOffersRootDTO) this.unmarshaller
                        .unmarshal(new FileReader(PATH_OFFERS.toAbsolutePath().toString()));

//        offersDTOs.getOffers()
//                .forEach(offr -> System.out.printf("%s %s %.2f %s%n",
//                        offr.getAgentName().getName(), offr.getApartmentId().getId(), offr.getPrice(), offr.getPublishedOn()));

        return offersDTOs
                .getOffers()
                .stream()
                .map(this::importOffer)
                .collect(Collectors.joining("\n"));
    }

    private String importOffer(ImportFromXMLOneOfferDTO dto) {
        Set<ConstraintViolation<ImportFromXMLOneOfferDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid offer";  //Positive number price is not
        }

        String agentName = dto.getAgentName().getName();
        Optional<Agent> optAgent = this.agentService.findByFirstNameOrLastName(agentName);
        if (optAgent.isEmpty()){
            return "Invalid offer"; //Agent does not exist in the database
        }

        long apId = dto.getApartmentId().getId();
        Optional<Apartment> optApartment = this.apartmentService.findById(apId);

//        Optional<Offer> optOfferWithWhichAgentAndApartment = this.offerRepository.findByAgentIdAndApartmentId(
//                optAgent.get().getId(), optAgent.get().getFirstName(), optAgent.get().getEmail(),
//                optApartment.get().getId());
//        if (optOfferWithWhichAgentAndApartment.isPresent()) {
//            return "Invalid offer";  //already have an offer with the same agent, but also with the same apartment
//        }

        Offer offer = this.modelMapper.map(dto, Offer.class);
        offer.setAgent(optAgent.get());
        offer.setApartment(optApartment.get());

        this.offerRepository.save(offer);

        return "Successfully imported offer " + String.format("%.2f", offer.getPrice());

    }

    @Override
    public String exportOffers() {
        List<OutputOneOfferTextDTO> bestOffers = this.offerRepository.findBestOffers();

        return bestOffers.stream()
                .map(o -> {
                    return String.format("Agent %s with offer №%d:\n" +
                                    "   \t-Apartment area: %.2f\n" +
                                    "   \t--Town: %s\n" +
                                    "   \t---Price: %.2f$", o.getFullName(), o.getOfferId(),
                            o.getApartmentArea(),
                            o.getTownName(),
                            o.getPrice().setScale(2, RoundingMode.HALF_UP));
                }).collect(Collectors.joining(System.lineSeparator()));
    }
}
