package softuni.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.models.User;
import softuni.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(User user) {
        User found = this.userRepository.findByUsername(user.getUsername());

        if (found == null) {
            this.userRepository.save(user);
        }
    }
}
