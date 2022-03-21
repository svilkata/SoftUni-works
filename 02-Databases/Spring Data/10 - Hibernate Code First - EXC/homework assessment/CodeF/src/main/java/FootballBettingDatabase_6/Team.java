package FootballBettingDatabase_6;

import javax.persistence.*;
import java.io.File;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{

    private String name;
    private File logo;
    private String initials;
    private Color primary_color;
    private Color secondary_color;
    private Town town;
    private BigDecimal budget;

    @Column(length = 45, nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "BLOB")
    public File getLogo() {
        return logo;
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }

    @Column(length = 3)
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @OneToOne
    @JoinColumn(name = "primary_kit_color")
    public Color getPrimary_color() {
        return primary_color;
    }

    public void setPrimary_color(Color primary_color) {
        this.primary_color = primary_color;
    }

    @OneToOne
    @JoinColumn(name = "secondary_kit_color")
    public Color getSecondary_color() {
        return secondary_color;
    }

    public void setSecondary_color(Color secondary_color) {
        this.secondary_color = secondary_color;
    }

    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
