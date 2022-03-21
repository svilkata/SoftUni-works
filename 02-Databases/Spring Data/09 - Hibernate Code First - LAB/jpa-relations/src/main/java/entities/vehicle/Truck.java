package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
@DiscriminatorValue("truck")
public class Truck extends TransportationVehicle {
    private static final String TRUCK_TYPE = "Truck";

    public Truck(double price, int loadCapacity) {
        super(TRUCK_TYPE, price, loadCapacity);
    }
}
