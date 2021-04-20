package enterNumbers;

public class NotIntegerNum extends NumberFormatException {
    public NotIntegerNum() {
        super("You entered not Integer number or text instead of number");
    }


}