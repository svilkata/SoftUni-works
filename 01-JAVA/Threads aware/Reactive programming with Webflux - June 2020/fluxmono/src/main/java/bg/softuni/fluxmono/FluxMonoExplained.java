package bg.softuni.fluxmono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FluxMonoExplained {
    private User user1 = new User().setFirstName("A").setLastName("B");
    private User user2 = new User().setFirstName("A1").setLastName("B1");

    public User getUser() {
        if (isAuthenticated()) {
            return user1;
        } else {
            return null;
        }
    }

    public Mono<User> getUserReactive() {
        if (isAuthenticated()) {
            return Mono.just(user1);
        } else {
            return Mono.empty();
        }
    }

    public Optional<User> getUserOpt() {
        if (isAuthenticated()) {
            return Optional.of(user1);
        } else {
            return Optional.empty();
        }
    }

    public List<User> getAllUsers() {
        return List.of(user1, user2);
    }

    public Flux<User> getAllUsersReactive() {
        return Flux.just(user1, user2);
    }


    private boolean isAuthenticated() {
        return new Random().nextBoolean();
    }

}

class User {
    private String firstName, lastName;

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
