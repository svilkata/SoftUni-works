package commandPattern;

public class IncreaseProductPriceCommand implements Command{
    private int amount;
    private Product product;

    public IncreaseProductPriceCommand (Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String execute() {
        this.product.increasePrice(this.amount);

        return String.format("The price for %s has been increased by %d",
                this.product.getName(), this.amount);
    }
}
