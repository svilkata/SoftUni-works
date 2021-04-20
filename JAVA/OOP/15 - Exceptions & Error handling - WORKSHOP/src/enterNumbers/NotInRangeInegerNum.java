package enterNumbers;

public class NotInRangeInegerNum extends Exception {
    public NotInRangeInegerNum() {
        super("You entered an Integer number which is not in the range 1-100");
    }
}
