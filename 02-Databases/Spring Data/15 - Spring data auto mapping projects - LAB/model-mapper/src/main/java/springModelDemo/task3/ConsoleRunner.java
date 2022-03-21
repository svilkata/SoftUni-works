package springModelDemo.task3;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.convert.TypeMapper;
import org.springframework.stereotype.Component;
import springModelDemo.task3.Dto.EmployeeSpringDto;
import springModelDemo.task3.servicies.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeService;

    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.persist();

        this.employeeService.findEmployeesBornBefore(1990).forEach(System.out::println);

        List<Employee> all = this.employeeService.findAll();


        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, EmployeeSpringDto> employeeToCustom = mapper.createTypeMap(Employee.class, EmployeeSpringDto.class);
        employeeToCustom.addMappings(m -> m.map(
                source -> source.getManager().getLastName().length(),
                (EmployeeSpringDto::setManagerLastNameLength)));
//        EmployeeSpringDto dto = mapper.map(manager, EmployeeSpringDto.class);
//        employeeToCustom.<String>addMappings(m -> m.map(src -> src.getFirstName(),
//                ((destination, value) -> destination.setFirstName(value))));
//        System.out.println(dto);

        Converter<Employee, Integer> getLastNameLength =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().getLastName().length();

        employeeToCustom.addMappings(mapping ->
                mapping.when(Objects::nonNull)
                        .using(getLastNameLength)
                        .map(Employee::getManager,
                                EmployeeSpringDto::setManagerLastNameLength)
        );

        all
                .stream()
                .map(e -> employeeToCustom.map(e))
                .forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee(
                "Manager",
                "last",
                BigDecimal.ONE,
                LocalDate.now(),
                null);


        Employee employee1 = new Employee(
                "first",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        Employee employee2 = new Employee(
                "second",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        this.employeeService.save(manager);
        this.employeeService.save(employee1);
        this.employeeService.save(employee2);
    }
}



