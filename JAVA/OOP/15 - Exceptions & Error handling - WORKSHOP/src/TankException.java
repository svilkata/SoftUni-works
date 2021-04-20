import java.io.PrintStream;

public class TankException extends Exception {
    public TankException(String msg) {
        super(msg);
    }

    public TankException(String message, Exception cause) {
        super(message, cause);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
