package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@DiscriminatorValue("car")
public class Car extends Vehicle {
    private static final String CAR_TYPE = "Car";

    private int doors;

    public Car(int doors) {
        super(CAR_TYPE, 2500);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}


