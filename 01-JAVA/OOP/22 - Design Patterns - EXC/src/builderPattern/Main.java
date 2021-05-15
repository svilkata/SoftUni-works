package builderPattern;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ShopBuilder builder = new ShopBuilder();

        Shop shop = builder
                .withEmployees(new ArrayList<>())
                .withNameOfStore("Shopa")
                .withNameOfStore("")
                .withAddress("Tintiava")
                .withSecurity()
                .build();

        System.out.println();
    }
}
