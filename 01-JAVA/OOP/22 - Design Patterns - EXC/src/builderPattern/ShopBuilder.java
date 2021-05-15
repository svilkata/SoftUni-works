package builderPattern;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

public class ShopBuilder implements Builder<Shop> {
    private Map<String, String> products;
    private List<String> employees;
    private String nameOfStore;
    private String addressOfStore;
    private List<String> suppliers;
    private boolean hasSecurity;

    public ShopBuilder() {
    }

    public ShopBuilder withEmployees(List<String> employees) {
        this.employees = employees;
        return this;
    }

    public ShopBuilder withSecurity() {
        this.hasSecurity = true;
        return this;
    }

    public ShopBuilder withNameOfStore(String nameOfStore) {
        this.nameOfStore = nameOfStore;
        return this;
    }

    public ShopBuilder withAddress(String address){
        this.addressOfStore = address;
        return this;
    }


    @Override
    public Shop build() {
        Shop shop = new Shop();

        shop.setProducts(this.products);
        shop.setEmployees(this.employees);
        shop.setNameOfStore(this.nameOfStore);
        shop.setAddressOfStore(this.addressOfStore);
        shop.setSuppliers(this.suppliers);
        shop.setHasSecurity(this.hasSecurity);

        return shop;
    }
}
