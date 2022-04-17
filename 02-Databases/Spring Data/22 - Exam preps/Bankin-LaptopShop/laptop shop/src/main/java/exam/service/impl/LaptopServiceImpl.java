package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.json.LaptopSeedFromJSONDto;
import exam.model.entities.Laptop;
import exam.model.entities.Shop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final Path LAPTOPS_FILE_PATH = Path.of("src", "main", "resources", "files", "json", "laptops.json");
    private final LaptopRepository laptopRepository;


    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private ShopService shopService;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, Gson gson, ModelMapper modelMapper,
                             ValidationUtil validationUtil, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(LAPTOPS_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        LaptopSeedFromJSONDto[] laptopsSeedDtos = gson.fromJson(this.readLaptopsFileContent(), LaptopSeedFromJSONDto[].class);

        return Arrays.stream(laptopsSeedDtos)
                .map(this::importOneLaptop)
                .collect(Collectors.joining("\n"));
    }

    private String importOneLaptop(LaptopSeedFromJSONDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid Laptop";
        }

        Optional<Laptop> optLaptop = this.laptopRepository.findByMacAddress(dto.getMacAddress());
        if (optLaptop.isPresent()) {
            return "Invalid Laptop";  //Mac address already exists
        }

        Optional<Shop> optShop = this.shopService.findShopByName(dto.getShop().getName());

        Laptop laptop = this.modelMapper.map(dto, Laptop.class);
        laptop.setShop(optShop.get());
        this.laptopRepository.save(laptop);

        return String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                dto.getMacAddress(), dto.getCpuSpeed(), dto.getRam(), dto.getStorage());
//        laptop mac address – cpu – ram -hdd
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();

        this.laptopRepository.extractBestLaptopsAllLaptops()
                .forEach(l ->
                        sb.append(String.format("Laptop - %s\n" +
                                "*Cpu speed - %.2f\n" +
                                "**Ram - %d\n" +
                                "***Storage - %d\n" +
                                "****Price - %.2f\n" +
                                "#Shop name - %s\n" +
                                "##Town - %s\n",
                                l.getMacAddress(), l.getCpuSpeed(), l.getRam(), l.getStorage(), l.getPrice(),
                                l.getShop().getName(), l.getShop().getTown().getName()))
                                .append(System.lineSeparator())
                );



        return sb.toString();
    }
}
