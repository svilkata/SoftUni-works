package softuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.models.User;
import softuni.services.UserService;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User first = new User("Qna", 22);
        userService.registerUser(first);

        User second = new User("Qna", 22);
        userService.registerUser(second);
    }
}
