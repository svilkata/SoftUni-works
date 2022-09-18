package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.MobileleUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileleUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public MobileleUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found."));

        return mapToUserDetails(userEntity);
    }

    //The purpose of this method is to map our user representation (UserEntity)
    // to the user representation in the Spring security world (UserDetails)
    // The only thing that Spring will provide to us is the username.
    // The username will come from the HTML login form.
    private static UserDetails mapToUserDetails(UserEntity userEntity){
        //Granted authority is the representation of a user role in the Spring world.
        // SimpleGrantedAuthority is an implementation of GrantedAuthority which Spring provides for our convenience
        // Our representation of role is UserRoleEntity
        List<GrantedAuthority> authorities = userEntity
                .getUserRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getUserRole().name()))
                .collect(Collectors.toList());

        //User is the Spring implementation of UserDetails interface.
//        return new User(
//                userEntity.getUsername(),
//                userEntity.getPassword(),
//                authorities
//        );
        return new MobileleUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}
