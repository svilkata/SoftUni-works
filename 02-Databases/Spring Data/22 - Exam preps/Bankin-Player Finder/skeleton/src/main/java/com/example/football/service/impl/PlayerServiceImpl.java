package com.example.football.service.impl;

import com.example.football.models.dto.ImportOnePlayerDTO;
import com.example.football.models.dto.ImportPlayersRootDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerServiceImpl implements PlayerService {
    private final Path PATH_PLAYERS = Path.of("src", "main", "resources", "files", "xml", "players.xml");

    private TeamRepository teamRepository;
    private TownRepository townRepository;
    private StatRepository statRepository;
    private final PlayerRepository playerRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, TownRepository townRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        JAXBContext context = JAXBContext.newInstance(ImportPlayersRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

        //Does not work
//        Converter<String, LocalDate> toLocalDate = s -> s.getSource() == null ? null :
//                LocalDate.parse(s.getSource(), DateTimeFormatter.ofPattern("\"dd/MM/yyyy\""));
////
//        modelMapper.addConverter(toLocalDate);
//
//        TypeMap<ImportOnePlayerDTO, Player> typeMap = this.modelMapper.createTypeMap(ImportOnePlayerDTO.class, Player.class);
//        this.dtoToPlayerMapper = typeMap.addMappings(m ->
//                m
//                        .using(toLocalDate)
//                        .map(ImportOnePlayerDTO::getBirthDate, Player::setBirthDate));

        //this works :)
//        this.modelMapper.addConverter((Converter<String, LocalDate>)
//                ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
//                String.class, LocalDate.class);

        //this also works :)
        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return String.join("\n", Files.readAllLines(PATH_PLAYERS));
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        ImportPlayersRootDTO playersDTOs =
                (ImportPlayersRootDTO) this.unmarshaller.unmarshal(new FileReader(PATH_PLAYERS.toAbsolutePath().toString()));

        return playersDTOs
                .getPlayers()
                .stream()
                .map(this::importPlayer)
                .collect(Collectors.joining("\n"));
    }

    private String importPlayer(ImportOnePlayerDTO dto) {
        Set<ConstraintViolation<ImportOnePlayerDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid Player";
        }

        Optional<Player> optPlayer = this.playerRepository.findByEmail(dto.getEmail());

        if (optPlayer.isPresent()) {
            return "Invalid Player";
        }

        Optional<Team> team = this.teamRepository.findByName(dto.getTeam().getName());
        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());
        Optional<Stat> stat = this.statRepository.findById(dto.getStat().getId());

        Player player = this.modelMapper.map(dto, Player.class);

        player.setTown(town.get());
        player.setTeam(team.get());
        player.setStat(stat.get());

        this.playerRepository.save(player);

        return "Successfully imported Player " + player.getFirstName() + " " + player.getLastName() +
                " - " + player.getPosition().toString();
    }

    @Override
    public String exportBestPlayers() {
        LocalDate after = LocalDate.of(1995, 1, 1);
        LocalDate before = LocalDate.of(2003, 1, 1);

        List<Player> players =
                this.playerRepository
                        .findAllByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after, before);

        return players
                .stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }
}
