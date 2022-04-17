package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dtos.json.CustomerSeedFromJSONDto;
import exam.model.entities.Customer;
import exam.model.entities.Shop;
import exam.model.entities.Town;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
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
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";
    private final CustomerRepository customerRepository;
    private final TownService townService;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownService townService, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.customerRepository = customerRepository;
        this.townService = townService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        CustomerSeedFromJSONDto[] customersSeedDtos = gson.fromJson(this.readCustomersFileContent(), CustomerSeedFromJSONDto[].class);

        return Arrays.stream(customersSeedDtos)
                .map(this::importOneCustomer)
                .collect(Collectors.joining("\n"));
    }

    private String importOneCustomer(CustomerSeedFromJSONDto dto) {
        boolean isValid = this.validationUtil.isValid(dto);
        if (!isValid) {
            return "Invalid customer";
        }

        Optional<Customer> optCustomer = this.customerRepository.findByEmail(dto.getEmail());
        if (optCustomer.isPresent()) {
            return "Invalid customer";  //customer already exists
        }

        Optional<Town> optTown = this.townService.findTownByName(dto.getTown().getName());

        Customer customer = this.modelMapper.map(dto, Customer.class);
        customer.setTown(optTown.get());
        this.customerRepository.save(customer);

        return "Successfully imported Customer " +
                dto.getFirstName() + " " + dto.getLastName() + " - " + dto.getEmail();
    }
}
