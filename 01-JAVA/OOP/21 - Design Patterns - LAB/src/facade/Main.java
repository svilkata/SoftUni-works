package facade;

public class Main {
    public static void main(String[] args) {

        Car car = new CarBuilder()
                .withType("Sedan")
                .withColor("Purple")
                .withNumberOfDoors(5)
                .build();

        int b = 5;

    }

}
