package bg.softuni.hateos.web;

import bg.softuni.hateos.mapping.StudentMapper;
import bg.softuni.hateos.model.dto.OrderDTO;
import bg.softuni.hateos.model.dto.StudentDTO;
import bg.softuni.hateos.model.entity.OrderEntity;
import bg.softuni.hateos.model.entity.StudentEntity;
import bg.softuni.hateos.repository.StudentsRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentsRepository studentsRepository;
    private final StudentMapper studentMapper;

    //WARNING - Normally we never inject repos in the controllers, we do it now only - for fun and quicker work
    public StudentsController(StudentsRepository studentsRepository, StudentMapper studentMapper) {
        this.studentsRepository = studentsRepository;
        this.studentMapper = studentMapper;
    }

    //колекции в HATEOS - използваме CollectionModel<EntityModel<DTO>>
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {
        List<EntityModel<StudentDTO>> allStudents = studentsRepository.findAll()
                .stream()
                .map(s -> studentMapper.mapEntityToDTO(s))
                .map(dto -> EntityModel.of(dto, createStudentLinks(dto)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(allStudents));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getOrders(@PathVariable("id") Long studentId) {
        StudentEntity studentEntity = studentsRepository
                .findById(studentId).orElseThrow();

        List<EntityModel<OrderDTO>> orders = studentEntity.getOrders()
                .stream()
                .map(o -> mapFromOrderEntityToOrderDTO(o))
                .map(dto -> EntityModel.of(dto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders));
    }

    private OrderDTO mapFromOrderEntityToOrderDTO(OrderEntity orderEntity) {
        return new OrderDTO().setId(orderEntity.getId())
                .setStudentId(orderEntity.getId())
                .setCourseId(orderEntity.getCourse().getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(@PathVariable("id") Long studentId, StudentDTO studentDTO) {
        //IMPLEMENTATION NOT IMPORTAMT
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentsById(@PathVariable("id") Long studentId) {
        StudentDTO studentDTO = studentsRepository.findById(studentId)
                .map(s -> studentMapper.mapEntityToDTO(s))
                .orElseThrow();

        return ResponseEntity.ok(
                EntityModel.of(studentDTO, createStudentLinks(studentDTO))
        );
    }

    private Link[] createStudentLinks(StudentDTO studentDTO) {
        List<Link> result = new ArrayList<>();

//        import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
        Link selfLink = linkTo(methodOn(StudentsController.class)
                .getStudentsById(studentDTO.getId())).withSelfRel();
        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentsController.class).
                update(studentDTO.getId(), studentDTO)).withRel("update");
        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentsController.class).
                getOrders(studentDTO.getId())).withRel("orders");
        result.add(orderLink);

        return result.toArray(new Link[0]);
    }
}



