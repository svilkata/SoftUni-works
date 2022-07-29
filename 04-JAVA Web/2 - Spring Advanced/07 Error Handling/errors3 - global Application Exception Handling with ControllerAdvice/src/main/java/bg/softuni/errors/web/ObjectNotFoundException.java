package bg.softuni.errors.web;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found!") - когато използваме Global controller error handling този ред също не важи
public class ObjectNotFoundException extends RuntimeException{
    private final Long objectId;

    public ObjectNotFoundException(Long objectId) {
        super("Object with id " + objectId + " not found!");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
