package _00_EnumDemo;

public enum AccountType {
    FREE("FREE"),
    SILVER("SILVER"),
    GOLD("GOLD"),
    TRIAL("TRIAL");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
