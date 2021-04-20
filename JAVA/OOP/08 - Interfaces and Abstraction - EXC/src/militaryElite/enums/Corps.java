package militaryElite.enums;

public enum Corps {
    AIRFORCE("Airforces"),
    MARINE("Marines");

    private final String name;

    Corps(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
