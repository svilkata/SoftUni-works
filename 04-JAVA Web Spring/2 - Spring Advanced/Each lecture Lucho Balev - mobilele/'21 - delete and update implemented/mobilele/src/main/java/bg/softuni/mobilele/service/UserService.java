package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.UserRoleEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final MobileleUserServiceImpl mobileleUserService;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, MobileleUserServiceImpl mobileleUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.mobileleUserService = mobileleUserService;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {
        //Правилният начин и да го вземем от базата данни
        UserRoleEntity adminRole = new UserRoleEntity().setId(1L).setUserRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole = new UserRoleEntity().setId(2L).setUserRole(UserRoleEnum.USER);

//        UserEntity admin = new UserEntity();
//        admin
//                .setUsername("admin")
//                .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()))
//                .setActive(true)
//                .setEmail(userRegisterDto.getEmail())
//                .setFirstName(userRegisterDto.getFirstName())
//                .setLastName(userRegisterDto.getLastName());
//
//        admin.setUserRoles(List.of(adminRole, userRole));
//        this.userRepository.save(admin);


        UserEntity pesho = new UserEntity();
        pesho
                .setUsername("gosho")
                .setPassword(passwordEncoder.encode("11111"))
                .setActive(true)
                .setEmail("gosho@sho")
                .setFirstName("Gosho")
                .setLastName("Goshov");
        pesho.setUserRoles(List.of(userRole));
        this.userRepository.save(pesho);

        //this is the Spring representation of a User - after register, we log-in the user directly
        UserDetails userDetailsPrincipal = mobileleUserService.loadUserByUsername(pesho.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetailsPrincipal,
                pesho.getPassword(),
                userDetailsPrincipal.getAuthorities()
                );
//        authentication.setAuthenticated(true);

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
