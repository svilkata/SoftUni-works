package exam.service.impl;

import exam.model.dtos.xml.TownOneImportFromXMLDto;
import exam.model.dtos.xml.TownsRootImportFromXMLDto;
import exam.model.entities.Town;
import exam.repository.TownRepository;
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
public class TownServiceImpl implements TownService {
    private final Path TOWNS_PATH_XML = Path.of("src", "main", "resources", "files", "xml", "towns.xml");

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(TOWNS_PATH_XML));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        TownsRootImportFromXMLDto towns = xmlParser.fromFile(TOWNS_PATH_XML.toString(), TownsRootImportFromXMLDto.class);

        return towns.getTowns()
                .stream()
                .map(this::importTown)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Optional<Town> findTownByName(String name) {
        return this.townRepository.findByName(name);
    }

    private String importTown(TownOneImportFromXMLDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid town";
        }

        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);

        return "Successfully imported Town " + town.getName();
    }
}
