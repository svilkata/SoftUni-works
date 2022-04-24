package com.example.nlt.service;

import com.example.nlt.models.Employee;
import com.example.nlt.models.Project;
import com.example.nlt.models.dto.ExportEmployeeDTO;
import com.example.nlt.models.dto.ImportEmployeesRootDTO;
import com.example.nlt.models.dto.ImportOneEmployeeDTO;
import com.example.nlt.repositories.EmployeeRepository;
import com.example.nlt.repositories.ProjectRepository;
import com.example.nlt.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Path xmlPath = Path.of("src/main/resources/files/xmls/employees.xml");
    private final EmployeeRepository employeeRepository;
    private final MyValidator validator;
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MyValidator validator,
                           @Qualifier(value = "withLocalDate") ModelMapper modelMapper, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    public String getEmployeesText() throws IOException {
        return Files.readString(xmlPath);
    }

    public String importEmplys() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(ImportEmployeesRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportEmployeesRootDTO rootDTO = (ImportEmployeesRootDTO) unmarshaller.unmarshal(new File(xmlPath.toString()));

        return rootDTO.getEmployees()
                .stream()
                .map(this::importEmployee)
                .collect(Collectors.joining("\n"));
    }

    private String importEmployee(ImportOneEmployeeDTO dto) {
        if (!this.validator.isValid(dto)) {
            return "Invalid Employee or Project";
        }

        Employee employee = this.modelMapper.map(dto, Employee.class);

        Optional<Project> optProject = this.projectRepository.findByName(dto.getProject().getName());
        if (optProject.isEmpty()) {
            return "Invalid Project Name";
        }

        employee.setProject(optProject.get());

        this.employeeRepository.save(employee);

        return "Imported Employee - " + employee.getFirstName() + " " + employee.getLastName();
    }

    public List<ExportEmployeeDTO> getEmployeesAbove25() {
        List<Employee> employees = this.employeeRepository.findByAgeGreaterThanOrderByProjectNameAsc(25);

        return employees.stream()
                .map(e -> new ExportEmployeeDTO(e))
                .collect(Collectors.toList());
    }
}
