package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAgentDto;
import softuni.exam.models.dto.ImportTownDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {



    private AgentRepository agentRepository;
    private TownRepository townRepository;

    private final Validator validator;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().create();

        this.modelMapper = new ModelMapper();

        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count()>0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        String stringPath = "src/main/resources/files/json/agents.json";
        Path path = Paths.get(stringPath);
        return Files.readAllLines(path).stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String importAgents() throws IOException {
        String agentJson = this.readAgentsFromFile();

        ImportAgentDto[] importAgentDtos = gson.fromJson(agentJson, ImportAgentDto[].class);

        return Arrays.stream(importAgentDtos)
                .map(this::importAgent)
                .collect(Collectors.joining(System.lineSeparator()));

    }

    private String importAgent(ImportAgentDto importAgentDto) {

        Set<ConstraintViolation<ImportAgentDto>> errors = validator.validate(importAgentDto);

        if(!errors.isEmpty()){
            return "Invalid agent";
        }

        Optional<Agent> optAgent = this.agentRepository.findByFirstName(importAgentDto.getFirstName());

        if(optAgent.isPresent()){
            return "Invalid agent";
        }

        Agent agent = this.modelMapper.map(importAgentDto,Agent.class);

        Optional<Town> town = this.townRepository.findByTownName(importAgentDto.getTown());

        if(!town.isPresent()) {
            return "Invalid agent";
        }

        agent.setTown(town.get());

        this.agentRepository.save(agent);

        return String.format("Successfully imported agent - %s %s", agent.getFirstName(),agent.getLastName());
    }
}
