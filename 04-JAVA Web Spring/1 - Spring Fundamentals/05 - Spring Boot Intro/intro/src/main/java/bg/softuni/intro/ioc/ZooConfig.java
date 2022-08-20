package bg.softuni.intro.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ZooConfig {
//    @Bean
//    public Animal cat() {
//        return new Cat();
//    }
//
//    @Bean
//    public Animal dog() {
//        return new Dog();
//    }

    @Bean(name = "cat")
    public Animal cat() {
        return new Cat();
    }

    @Bean("normalDog")
    public Animal dog() {
        return new Dog();
    }

    @Bean(name = "mySuperDog")
    public Animal superDog() {
        //todo: add superpower to this dog
        return new Dog(true);
    }
}



