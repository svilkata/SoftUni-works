package football;

import java.util.ArrayList;
import java.util.List;

import static football.Validator.checkIfValidName;

public class Team {
    private String name;
    private List<Player> players;

    public Team() {

    }

    public Team(String name) {
        try {
            this.setName(name);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        checkIfValidName(name);
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        Player test = checkIfPlayerInTeamPresent(playerName);
        if (test == null) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.",
                    playerName, this.name));
        } else {
            this.players.remove(test);
        }

    }

    private Player checkIfPlayerInTeamPresent(String playerName) {
        for (Player player : this.players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }

        return null;
    }

    public double getRating() {
        double raitingTeam = 0.0;
        for (Player player : players) {
            raitingTeam += player.overallSkillLevel();
        }
        return raitingTeam / this.players.size();
    }
}
