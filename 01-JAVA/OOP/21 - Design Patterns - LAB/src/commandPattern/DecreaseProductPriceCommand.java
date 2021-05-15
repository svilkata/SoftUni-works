package commandPattern;

public class DecreaseProductPriceCommand implements Command {
    private Product product;
    private int amount;

    public DecreaseProductPriceCommand(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String execute() {
        this.product.increasePrice(this.amount);

        return String.format("The price for %s has been decreased by %d",
                this.product.getName(), this.amount);
    }
}
