package bg.softuni.mobilele;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodePassword implements CommandLineRunner {
    private PasswordEncoder encoder;

    public EncodePassword(PasswordEncoder encoder){
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(encoder.encode("topsecret"));
    }
}
