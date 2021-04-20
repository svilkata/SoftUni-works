package box;

public class Validator {
    private Validator(){

    }

    public static void validateNonNegativeSize(double size, String source) {
        if (size <= 0) {
            throw new IllegalArgumentException(source + " cannot be zero or negative.");
        }
    }
}
