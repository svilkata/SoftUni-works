package militaryElite.utils;

import militaryElite.enums.Corps;
import militaryElite.enums.MissionState;

public class Validator {
    private Validator() {

    }

    public static void validateCorps(String corps) {
        if (!corps.equals(Corps.AIRFORCE.getName()) && !corps.equals(Corps.MARINE.getName())) {
            throw new IllegalArgumentException("Invalid corps!");
        }
    }

    public static boolean validateMissionState(String state) {
        if (!state.equals(MissionState.IN_PROGRESS.getState()) && !state.equals(MissionState.FINISHED.getState())) {
            return false;
        }

        return true;
    }
}
