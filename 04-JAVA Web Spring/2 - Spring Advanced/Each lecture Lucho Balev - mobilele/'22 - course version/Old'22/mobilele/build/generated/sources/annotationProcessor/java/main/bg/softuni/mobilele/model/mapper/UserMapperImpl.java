package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-15T23:29:14+0300",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userDtoToUserEntity(UserRegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( registerDto.getEmail() );
        userEntity.setPassword( registerDto.getPassword() );
        userEntity.setFirstName( registerDto.getFirstName() );
        userEntity.setLastName( registerDto.getLastName() );

        userEntity.setActive( true );

        return userEntity;
    }
}
