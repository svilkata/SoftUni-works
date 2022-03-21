package FootballBettingDatabase_6;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "colors")
public class Color extends BaseEntity{

    private String name;

    @Column(nullable = false, unique = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
