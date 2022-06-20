package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;

import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    //username is email
    public boolean login(UserLoginDto loginDto) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(loginDto.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("User with name [{}] not found.", loginDto.getUsername());
            return false;
        }

        var rawPassword = loginDto.getPassword();
        var encodedPassword = userOpt.get().getPassword(); //което се е записало в базата данни

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(UserEntity userEntity) {
        currentUser
                .setLoggedIn(true)
                .setName(userEntity.getFirstName() + " " + userEntity.getLastName())
                .setEmail(userEntity.getEmail());
    }

    public void logout() {
        currentUser.clear();
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

//        UserEntity newUser = new UserEntity()
//                .setActive(true)
//                .setEmail(userRegisterDto.getEmail())
//                .setFirstName(userRegisterDto.getFirstName())
//                .setLastName(userRegisterDto.getLastName())
//                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        newUser = userRepository.save(newUser);
        login(newUser);
    }
}
