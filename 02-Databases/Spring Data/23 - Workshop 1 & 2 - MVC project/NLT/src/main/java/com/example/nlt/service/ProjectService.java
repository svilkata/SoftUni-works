package com.example.nlt.service;

import com.example.nlt.models.Company;
import com.example.nlt.models.Project;
import com.example.nlt.models.dto.ImportOneProjectDTO;
import com.example.nlt.models.dto.ImportProjectsRootDTO;
import com.example.nlt.repositories.CompanyRepository;
import com.example.nlt.repositories.ProjectRepository;
import com.example.nlt.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final Path xmlPath = Path.of("src/main/resources/files/xmls/projects.xml");
    private final MyValidator myValidator;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(MyValidator myValidator,
                          @Qualifier("withLocalDate") ModelMapper modelMapper,
                          CompanyRepository companyRepository, ProjectRepository projectRepository) {
        this.myValidator = myValidator;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }

    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    public String getProjectsText() throws IOException {
        return Files.readString(xmlPath);
    }

    public String importProjs() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ImportProjectsRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportProjectsRootDTO rootDTO = (ImportProjectsRootDTO) unmarshaller.unmarshal(new FileReader(xmlPath.toString()));

        StringBuilder sb = new StringBuilder();
        List<ImportOneProjectDTO> projects = rootDTO.getProjects();
        for (ImportOneProjectDTO dto : projects) {
            if (!myValidator.isValid(dto)) {
                sb.append("Invalid Project\n");
                continue;
            }

            Project project = this.modelMapper.map(dto, Project.class);

            Optional<Company> optCompany = this.companyRepository.findByName(dto.getCompany().getName());
            project.setCompany(optCompany.get());

            this.projectRepository.save(project);
            sb.append("Created Project - ")
                    .append(project.getName())
                    .append(" for company ")
                    .append(project.getCompany().getName())
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getFinishedProducts() {
        List<Project> projects = this.projectRepository.findByIsFinishedTrueOrderByPaymentDesc();

        return projects.stream()
                .map(Project::toString)
                .collect(Collectors.joining("\n"));
    }
}
