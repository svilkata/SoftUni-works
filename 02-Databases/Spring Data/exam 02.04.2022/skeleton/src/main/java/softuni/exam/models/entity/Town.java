package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "town_name", nullable = false, unique = true)
    private String townName;

    @Column
    private int population;

//    @OneToMany(targetEntity = Agent.class, mappedBy = "town")
//    private Set<Agent> townAgents;
//
//    @OneToMany(targetEntity = Apartment.class, mappedBy = "town")
//    private Set<Apartment> townApartments;

//    public Set<Apartment> getTownApartments() {
//        return townApartments;
//    }
//
//    public void setTownApartments(Set<Apartment> townApartments) {
//        this.townApartments = townApartments;
//    }

    public Town() {
    }

    public long getId() {
        return id;
    }

    public String getTownName() {
        return townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

//    public Set<Agent> getTownAgents() {
//        return townAgents;
//    }
//
//    public void setTownAgents(Set<Agent> townAgents) {
//        this.townAgents = townAgents;
//    }
}
