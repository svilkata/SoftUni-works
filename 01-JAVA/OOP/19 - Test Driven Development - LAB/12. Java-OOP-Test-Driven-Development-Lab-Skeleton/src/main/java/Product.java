public class Product implements Comparable<Product> {
    public String label;
    public double price;
    public int quantity;

    public Product(String label, double price, int quantity) {
        this.label = label;
        this.price = price;
        this.quantity = quantity;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product) || obj ==null) {
            return false;
        }

        Product other = (Product) obj;
        return  other.getLabel().equals(this.label);
//        return super.equals(obj);
    }

    @Override
    public int compareTo(Product other) {
        return this.getLabel().compareTo(other.getLabel());
    }
}
