package restaurant;

import java.math.BigDecimal;

public class Salmon extends MainDish {
    private static final double SALMON_GRAMS = 22.00;

    public Salmon(String name, BigDecimal bigDecimal) {
        super(name, bigDecimal, Salmon.SALMON_GRAMS);
    }
}
