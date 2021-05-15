package militaryElite.enums;

public enum MissionState {
    IN_PROGRESS("inProgress"),
    FINISHED("finished");

    private final String state;

    private MissionState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
