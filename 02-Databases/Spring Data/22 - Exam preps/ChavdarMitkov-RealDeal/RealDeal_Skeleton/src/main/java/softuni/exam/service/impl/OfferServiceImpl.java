package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferSeedOneDto;
import softuni.exam.models.dto.OffersSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final SellerRepository sellerRepository;
    private final CarRepository carRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public OfferServiceImpl(SellerRepository sellerRepository, OfferRepository offerRepository, CarRepository carRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        OffersSeedRootDto sellersSeedRootDto = xmlParser.fromFile(OFFERS_FILE_PATH, OffersSeedRootDto.class);

        return sellersSeedRootDto
                .getOffers()
                .stream()
                .map(this::importAnOffer)
                .collect(Collectors.joining("\n"));
    }

    private String importAnOffer(OfferSeedOneDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid offer";
        }

        Optional<Offer> optOffer = this.offerRepository.findByDescriptionAndAddedOn(dto.getDescription(),
                LocalDateTime.parse(dto.getAddedOn(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (optOffer.isPresent()) {
            return "Invalid offer - this offer already exists";
        }

        Offer offer = this.modelMapper.map(dto, Offer.class);
        Car validCar = this.carRepository.findById(dto.getCar().getId()).get();
        offer.setCar(validCar);

        Seller seller = this.sellerRepository.findById(dto.getSeller().getId()).get();
        offer.setSeller(seller);

        this.offerRepository.save(offer);

        return "Successfully import offer  " + offer.getAddedOn() + " - " + offer.isHasGoldStatus();

    }
}
