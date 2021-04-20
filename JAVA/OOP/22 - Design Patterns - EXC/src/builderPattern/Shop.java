package builderPattern;

import java.util.List;
import java.util.Map;

public class Shop implements Product{
    private Map<String, String> products;
    private List<String> employees;
    private String nameOfStore;
    private String addressOfStore;
    private List<String> suppliers;
    private boolean hasSecurity;

    public boolean isHasSecurity() {
        return hasSecurity;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public Shop() {}

    public Map<String, String> getProducts() {
        return this.products;
    }

    public void setProducts(Map<String, String> products) {
        this.products = products;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public String getNameOfStore() {
        return nameOfStore;
    }

    public void setNameOfStore(String nameOfStore) {
        this.nameOfStore = nameOfStore;
    }

    public String getAddressOfStore() {
        return addressOfStore;
    }

    public void setAddressOfStore(String addressOfStore) {
        this.addressOfStore = addressOfStore;
    }

    public List<String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<String> suppliers) {
        this.suppliers = suppliers;
    }
}
