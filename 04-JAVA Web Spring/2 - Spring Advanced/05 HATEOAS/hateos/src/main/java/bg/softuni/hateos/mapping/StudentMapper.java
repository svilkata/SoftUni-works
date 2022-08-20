package bg.softuni.hateos.mapping;

import bg.softuni.hateos.model.dto.StudentDTO;
import bg.softuni.hateos.model.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO mapEntityToDTO(StudentEntity studentEntity);

}
