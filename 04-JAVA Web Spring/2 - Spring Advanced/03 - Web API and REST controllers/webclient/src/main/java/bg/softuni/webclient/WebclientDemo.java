package bg.softuni.webclient;

import bg.softuni.webclient.model.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebclientDemo implements CommandLineRunner {
    @Override
    public void run(String... args) {

        WebClient client = WebClient.create("https://reqres.in/");

        UserDTO user = client.get()
                .uri("api/users/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
//                .bodyToFlux(UserDTO[].class)   - масив от обекти
                .bodyToMono(UserDTO.class)//връща само 1 или 0 обекти
                .block(); //нишката няма да се задържи и ще бъде release-вана и ше може да изчака, за да се expose-не за обслужването на някой друг request

        System.out.println(user);
    }
}
