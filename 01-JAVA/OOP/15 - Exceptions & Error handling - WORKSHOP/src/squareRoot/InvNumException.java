package squareRoot;

public class InvNumException extends NumberFormatException {
    public InvNumException() {
        super("Invalid number");
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
