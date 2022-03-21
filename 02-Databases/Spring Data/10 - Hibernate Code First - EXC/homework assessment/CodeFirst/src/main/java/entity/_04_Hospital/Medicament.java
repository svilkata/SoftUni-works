package entity._04_Hospital;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name =  "medicaments")
public class Medicament extends GP {

    private String name;

    public Medicament() {
    }

    @Column(name = "name", length = 80, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
