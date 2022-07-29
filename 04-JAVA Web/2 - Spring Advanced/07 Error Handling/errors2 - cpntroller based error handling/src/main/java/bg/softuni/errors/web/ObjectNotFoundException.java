package bg.softuni.errors.web;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found!") - когато използваме Controller-based error handling този ред не важи
public class ObjectNotFoundException extends RuntimeException{
    private final Long productId;

    public ObjectNotFoundException(Long productId) {
        super("Cannot find object with id " + productId);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
