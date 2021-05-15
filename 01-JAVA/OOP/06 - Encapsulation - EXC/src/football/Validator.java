package football;

import java.util.List;

public class Validator {
    public static void checkIfValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

    public static void checkIfValidStats(int stat, String statName) {
        if (stat < 0 || stat > 100) {
            throw new IllegalArgumentException(String.format("%s should be between 0 and 100.", statName));
        }
    }

    public static void checkIfTeamPresent(List<Team> teams, String teamNameToCheck) {
        boolean isTeamPresent = false;
        for (Team tm : teams) {
            if (tm.getName().equals(teamNameToCheck)) {
                isTeamPresent = true;
                break;
            }
        }

        if (!isTeamPresent) {
            throw new IllegalArgumentException(String.format("Team %s does not exist.", teamNameToCheck));
        }
    }


}
