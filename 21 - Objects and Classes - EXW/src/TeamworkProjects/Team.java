package TeamworkProjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String teamName;
    //    private String creatorName;
    private List<String> membersNames = new ArrayList<>();

    public Team(String teamName, List<String> membersNames) {
        this.teamName = teamName;
        this.membersNames = membersNames;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getMembersNames() {
        return this.membersNames;
    }

    String getMember(int i) {
        return membersNames.get(i);
    }


    //@Override
    public String toStringSpecial() {
        String result = String.format("%s%n- %s%n", this.teamName, this.getMember(0));
        //this.membersNames.remove(0);

        List<String> tmp = new ArrayList<>();
        for (int i = 1; i < this.getMembersNames().size(); i++) {
            tmp.add(this.getMember(i));
        }

        tmp =
                tmp
                        .stream()
                        //.filter(p -> !p.compareTo(getMember(0))
                        .sorted((p1, p2) -> p1.compareTo(p2))
                        .collect(Collectors.toList());

        for (int i = 0; i < tmp.size(); i++) {
            //String membersName = membersNames.get(i);
            result += String.format("-- %s%n", tmp.get(i));
        }

        return result;
    }
}
