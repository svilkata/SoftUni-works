package commandPattern;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Phone", 500);
        ModifyPrice modifier = new ModifyPrice();

        modifier.addCommand(new IncreaseProductPriceCommand(product, 100));
        modifier.addCommand(new DecreaseProductPriceCommand(product, 20));
        modifier.addCommand(new DecreaseProductPriceCommand(product, 45));

        modifier.invoke();

        System.out.println(product.getPrice());
    }
}
