package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportFromJSONAgentDTO;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
        this.gson = new GsonBuilder().create();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        Path pathAgents = Path.of("src", "main", "resources", "files", "json", "agents.json");

        return String.join("\n", Files.readAllLines(pathAgents));
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();
        ImportFromJSONAgentDTO[] importAgentsDTOS = this.gson.fromJson(json, ImportFromJSONAgentDTO[].class);

        String result = Arrays.stream(importAgentsDTOS)
                .map(this::importAgent)
                .collect(Collectors.joining("\n"));

        return result;
    }

    @Override
    public Optional<Agent> findByFirstNameOrLastName(String agentName) {
        return this.agentRepository.findByFirstNameOrLastName(agentName);
    }

    private String importAgent(ImportFromJSONAgentDTO dto) {
        Set<ConstraintViolation<ImportFromJSONAgentDTO>> validationErrors = this.validator.validate(dto);

        if (!validationErrors.isEmpty()){
            return "Invalid agent";
        }

        Optional<Agent> optAgent = this.agentRepository.findByFirstNameOrLastName(dto.getFirstName());
        if (optAgent.isPresent()) {
            return "Invalid agent";
        }

        Agent agent = this.modelMapper.map(dto, Agent.class);
        Optional<Town> town = this.townRepository.findBytownName(dto.getTown());
        agent.setTown(town.get());

        this.agentRepository.save(agent);
        return "Successfully imported agent " + agent.getFirstName() + " " + agent.getLastName();
    }
}
