package FootballBettingDatabase_6;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity{

    private String name;
    private CompetitionType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "competition_type")
    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }
}
