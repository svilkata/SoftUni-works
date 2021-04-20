package carShop;

public class Audi extends CarImpl implements Rentable{
    private Integer minimumRentalDays;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced,
                Integer minimumRentalDays, Double pricePerDay) {
        super(model, color, horsePower, countryProduced);
        this.minimumRentalDays = minimumRentalDays;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Integer getMinRentDay() {
        return this.minimumRentalDays;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String toString() {
        return String.format("%s%nMinimum rental period of %d days. Price per day %f",
                super.toString(),
                this.getMinRentDay(),
                this.getPricePerDay()
                );
    }
}
