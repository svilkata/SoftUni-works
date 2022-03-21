package FootballBettingDatabase_6;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    private String id;
    private String name;
    private Set<Continent> continent;

    @Id
    @Column(length = 3, nullable = false, unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "countries")
    public Set<Continent> getContinent() {
        return continent;
    }

    public void setContinent(Set<Continent>  continent) {
        this.continent = continent;
    }
}
