package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerSeedOneDto;
import softuni.exam.models.dto.SellersSeedRootDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        SellersSeedRootDto sellersSeedRootDto = xmlParser.fromFile(SELLERS_FILE_PATH, SellersSeedRootDto.class);

        return sellersSeedRootDto
                .getSellers()
                .stream()
                .map(this::importASeller)
                .collect(Collectors.joining("\n"));
    }

    private String importASeller(SellerSeedOneDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid seller";
        }

        Optional<Seller> optSeller = this.sellerRepository.findByEmail(dto.getEmail());
        if (optSeller.isPresent()) {
            return "Invalid seller - this seller already exists";
        }

        Seller seller = this.modelMapper.map(dto, Seller.class);

        this.sellerRepository.save(seller);

        return "Successfully import seller " + seller.getLastName() + " - " + seller.getEmail();
    }
}
