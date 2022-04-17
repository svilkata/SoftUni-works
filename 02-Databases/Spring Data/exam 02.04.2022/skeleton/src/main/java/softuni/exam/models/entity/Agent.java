package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne
    private Town town;

//    @OneToMany()
//    private Set<Offer> agentOffers;
//
//    public Set<Offer> getAgentOffers() {
//        return agentOffers;
//    }
//
//    public void setAgentOffers(Set<Offer> agentOffers) {
//        this.agentOffers = agentOffers;
//    }

    public Agent() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Town getTown() {
        return town;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTown(Town town) {
        this.town = town;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Agent agent = (Agent) o;
//        return id == agent.id && Objects.equals(firstName, agent.firstName) && Objects.equals(email, agent.email);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, email);
//    }
}
