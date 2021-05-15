package team;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Loko");
        Person playerOne = new Person("Pesho", "Goshov", 22);

        team.getFirstTeam().add(playerOne);

        System.out.println(team.getFirstTeam().size());

    }
}
