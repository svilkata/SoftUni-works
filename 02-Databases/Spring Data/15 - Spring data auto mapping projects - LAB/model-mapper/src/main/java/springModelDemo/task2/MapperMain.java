package springModelDemo.task2;

import org.modelmapper.ModelMapper;
import springModelDemo.task2.dto.ManagerDTO;
import springModelDemo.task2.entities.Address;
import springModelDemo.task2.entities.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {
    public static void main(String[] args) {
        ModelMapper mapper = new ModelMapper();

        Address address = new Address("boris 3", 3, "Sofia", "Bulgaria");

        Employee manager = new Employee("Mr.", "Manager",
                BigDecimal.ZERO, LocalDate.now(), address, true);

        Employee firstEmpl = new Employee("Mr.", "Employee 1",
                BigDecimal.ONE, LocalDate.now(), address, true);

        Employee secondEmpl = new Employee("Mr.", "Employee 2",
                BigDecimal.TEN, LocalDate.now(), address, true);

        manager.addEmployee(firstEmpl);
        manager.addEmployee(secondEmpl);

        ManagerDTO dto = mapper.map(manager, ManagerDTO.class);

        System.out.println(dto);
    }
}
