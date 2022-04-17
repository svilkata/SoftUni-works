package exam.service.impl;

import exam.model.dtos.xml.ShopOneImportFromXMLDto;
import exam.model.dtos.xml.ShopsRootImportFromXMLDto;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final String SHOPS_PATHSTRING_XML = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownService townService, ModelMapper modelMapper,
                           ValidationUtil validationUtil, XmlParser xmlParser) {
        this.shopRepository = shopRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOPS_PATHSTRING_XML));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ShopsRootImportFromXMLDto shops = xmlParser.fromFile(SHOPS_PATHSTRING_XML,
                ShopsRootImportFromXMLDto.class);

        return shops.getShops()
                .stream()
                .map(this::importShop)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Optional<Shop> findShopByName(String name) {
        return this.shopRepository.findByName(name);
    }

    private String importShop(ShopOneImportFromXMLDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid shop";
        }

        Optional<Shop> optShop = this.shopRepository.findByName(dto.getName());
        if (optShop.isPresent()) {
            return "Invalid shop";  //shop already exists
        }

        Optional<Town> optTown = this.townService.findTownByName(dto.getTown().getName());

        Shop shop = this.modelMapper.map(dto, Shop.class);
        shop.setTown(optTown.get());
        this.shopRepository.save(shop);

        return "Successfully imported Shop " + shop.getName() + " - " + shop.getIncome();
    }
}
