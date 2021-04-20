package HotelReservation;

enum Discount {
    None(0),
    SecondVisit(10),
    VIP(20);

    private final int discountPercents;

    Discount (int percents) {
        this.discountPercents = percents;
    }

    public double getDiscount() {
        return (100 - this.discountPercents) / 100.0;
    }
}
