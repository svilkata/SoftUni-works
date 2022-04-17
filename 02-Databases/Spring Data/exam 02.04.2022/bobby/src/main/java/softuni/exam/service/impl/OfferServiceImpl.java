package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xml.OfferSeedRootDTO;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.models.entity.enums.ApartmentType.three_rooms;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final ApartmentService apartmentService;
    private final AgentService agentService;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper,
                            ValidationUtil validationUtil, XmlParser xmlParser,
                            ApartmentService apartmentService, AgentService agentService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.apartmentService = apartmentService;
        this.agentService = agentService;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(OFFERS_FILE_PATH, OfferSeedRootDTO.class)
                .getOffers()
                .stream()
                .filter(offerSeedDTO -> {
                    boolean isValid = validationUtil.isValid(offerSeedDTO);
                    if (agentService.FindAgentByName(offerSeedDTO.getAgent().getName()) == null){
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported offer %.2f",
                                    offerSeedDTO.getPrice())
                                    : "Invalid offer")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(offerSeedDTO -> {
                    Offer offer = modelMapper.map(offerSeedDTO, Offer.class);
                        offer.setApartment(apartmentService.FindApartmentById(offerSeedDTO.getApartment().getId()));
                        offer.setAgent(agentService.FindAgentByName(offerSeedDTO.getAgent().getName()));
                    return offer;
                })
                .forEach(offerRepository::save);
        return sb.toString();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();
        ApartmentType apartmentType = three_rooms;
        offerRepository.FindAllByApartmentType(apartmentType)
                .forEach(offer-> {
                    sb.append(String.format("Agent %s %s with offer №%d:\n" +
                                            "   \t\t-Apartment area: %.2f\n" +
                                            "   \t\t--Town: %s\n" +
                                            "   \t\t---Price: %.2f$\n",
                                    offer.getAgent().getFirstName(), offer.getAgent().getLastName(),
                                    offer.getId(), offer.getApartment().getArea(),
                                    offer.getApartment().getTown().getTownName(), offer.getPrice()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
