package commandPattern;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void increasePrice(int amount) {
        this.setPrice(this.price + amount);
    }

    public void decreasePrice(int amount) {
        this.setPrice(this.price - amount);
    }
}
