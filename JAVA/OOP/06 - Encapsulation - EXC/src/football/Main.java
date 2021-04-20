package football;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static football.Validator.checkIfTeamPresent;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Team> teams = new ArrayList<>();

        while (!"END".equals(input)) {
            String[] data = input.split(";");

            switch (data[0]) {
                case "Team": //add team
                    Team team = new Team(data[1]);
                    teams.add(team);
                    break;
                case "Add": //add player
                    try {
                        checkIfTeamPresent(teams, data[1]);
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }

                    for (Team tm : teams) {
                        if (tm.getName().equals(data[1])) {
                            Player playerToAdd = null;
                            try {
                                playerToAdd = new Player(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                                        Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]));
                                tm.addPlayer(playerToAdd);
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }


                    break;
                case "Remove": //remove player
                    for (Team tm : teams) {
                        if (tm.getName().equals(data[1])) {
                            try {
                                tm.removePlayer(data[2]);
                            } catch (IllegalArgumentException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                    break;
                case "Rating": //print team rating
                    for (Team tm : teams) {
                        if (tm.getName().equals(data[1])) {
                            System.out.println(tm.getName() + " - " + Math.round(tm.getRating()));
                        }
                    }
                    break;
            }

            input = sc.nextLine();
        }
    }
}
