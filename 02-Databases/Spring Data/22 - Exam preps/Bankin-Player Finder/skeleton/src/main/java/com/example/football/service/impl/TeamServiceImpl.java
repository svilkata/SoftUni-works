package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private TownRepository townRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

        return String.join("\n", Files.readAllLines(path));
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();
        ImportTeamDTO[] importTeamsDTOS = this.gson.fromJson(json, ImportTeamDTO[].class);

        String result = Arrays.stream(importTeamsDTOS)
                .map(this::importTeam)
                .collect(Collectors.joining("\n"));

        return result;
    }

    private String importTeam(ImportTeamDTO dto) {
        Set<ConstraintViolation<ImportTeamDTO>> validationErrors = this.validator.validate(dto);

        if (!validationErrors.isEmpty()){
            return "Invalid Team";
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());
        if (optTeam.isPresent()) {
            return "Invalid Team";
        }

        Team team = this.modelMapper.map(dto, Team.class);
        Town town = this.townRepository.findByName(dto.getTownName()).get();

        team.setTown(town);

        this.teamRepository.save(team);
        return "Successfully imported Team " + team.toString();

    }
}
