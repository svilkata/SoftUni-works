//public class Car {
//    private String brand;
//    private String model;
//    private int horsePower;
//
//    public Car() {
//
//    }
//
//    public Car(String brand, String model, int horsePower) {
//        this.brand = brand;
//        this.model = model;
//        this.horsePower = horsePower;
//    }
//
//
//    public void setBrand(String newBrand) {
//        this.brand = newBrand;
//    }
//
//    public void setModel(String newModel) {
//        this.model = newModel;
//    }
//
//    public void setHorsePower(int newHorsePower) {
//        this.horsePower = newHorsePower;
//    }
//
//    public String getBrand() {
//        return this.brand;
//    }
//
//    public String getModel() {
//        return this.model;
//    }
//
//    public int getHorsePower() {
//        return this.horsePower;
//    }
//
//    public void increaseHP(int additionalHp) {
//        this.horsePower += additionalHp;
//    }
//
//    public String carInfo() {
//        return String.format("The car is: %s %s - %d HP.",
//                this.getBrand(), this.getModel(), this.getHorsePower());
//    }
//
//
//    @Override
//    public String toString() {
//        return getBrand() + " " + getModel() + " " + getHorsePower();
//    }
//
//
//}