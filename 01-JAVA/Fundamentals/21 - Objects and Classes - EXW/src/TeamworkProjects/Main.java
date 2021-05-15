package TeamworkProjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());// Брой teams за регистриране

        String input = sc.nextLine();
        List<Team> teamList = new ArrayList<>();
        //цикъл за въвеждане на тимове
        for (int k = 0; k < n; k++) {
            String[] tokensCreateTeam = input.split("-");

            boolean isTeamUserAlreadyCreated = false;
            for (int i = 0; i < teamList.size(); i++) {
                Team team = teamList.get(i); //проверки
                if (tokensCreateTeam[1].equals(team.getTeamName())) {
                    System.out.println(String.format("Team %s was already created!", tokensCreateTeam[1]));
                    isTeamUserAlreadyCreated = true;
                    break;
                } else if (tokensCreateTeam[0].equals(team.getMember(0))) {
                    System.out.println(String.format("%s cannot create another team!", tokensCreateTeam[0]));
                    isTeamUserAlreadyCreated = true;
                    break;
                }
            }

            if (!isTeamUserAlreadyCreated) { //създаваме нов тим
                List<String> members = new ArrayList<>(Arrays.asList(tokensCreateTeam[0]));
                Team newTeam = new Team(tokensCreateTeam[1], members);
                teamList.add(newTeam);
                System.out.println(String.format("Team %s has been created by %s!", tokensCreateTeam[1], tokensCreateTeam[0]));

            }

            input = sc.nextLine();
        }


        while (!"end of assignment".equals(input)) {
            String[] tokensJoinMember = input.split("->");
            boolean isMemberAlready = false;
            int indexTeamExist = -1;

            for (int i = 0; i < teamList.size(); i++) { //Pesho->AiNaBira
                if (indexTeamExist == -1) {
                    if (tokensJoinMember[1].equals(teamList.get(i).getTeamName())) { //обикаляме докато намерим съществуващ тим
                        //isTeamCreated = true;
                        indexTeamExist = i;
                        //break;
                    }
                }

                for (int j = 0; j < teamList.get(i).getMembersNames().size(); j++) {
                    if (teamList.get(i).getMember(j).equals(tokensJoinMember[0])) {
                        isMemberAlready = true;
                        break;
                    }
                }
            }

            if (indexTeamExist == -1) {
                System.out.println(String.format("Team %s does not exist!", tokensJoinMember[1]));
            } else if (isMemberAlready) {
                System.out.println(String.format("Member %s cannot join team %s!", tokensJoinMember[0], tokensJoinMember[1]));
            } else {
                //isTeamCreated && isCurrentPersonNewMember
                teamList.get(indexTeamExist).getMembersNames().add(tokensJoinMember[0]);

                //allPersons.add(tokensJoinMember[0]);
            }

            input = sc.nextLine();
        }

        teamList
                .stream()
                .filter(p -> p.getMembersNames().size() > 1)
                .sorted((p1, p2) -> Integer.compare(p2.getMembersNames().size(), p1.getMembersNames().size()))
                .sorted((p1, p2) -> p1.getTeamName().compareTo(p2.getTeamName()))
                .forEach(p -> System.out.print(p.toStringSpecial()));


        System.out.println("Teams to disband:");
        teamList
                .stream()
                .filter(p -> p.getMembersNames().size() == 1)
                .forEach(p -> System.out.println(p.getTeamName()));
    }
}
