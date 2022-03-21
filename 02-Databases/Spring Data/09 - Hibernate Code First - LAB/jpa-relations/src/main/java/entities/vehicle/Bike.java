package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bikes")
@DiscriminatorValue("bike")
public class Bike extends Vehicle {
    private static final String BIKE_TYPE = "Bike";

    private int gearCount;

    public Bike(int gearCount) {
        super(BIKE_TYPE, 250);
        this.gearCount = gearCount;
    }

    public int getGearCount() {
        return gearCount;
    }

    public void setGearCount(int gearCount) {
        this.gearCount = gearCount;
    }
}
