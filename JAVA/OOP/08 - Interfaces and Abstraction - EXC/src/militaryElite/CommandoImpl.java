package militaryElite;

import militaryElite.enums.Corps;
import militaryElite.interfaces.Commando;
import militaryElite.models.Mission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps, Mission... missions) {
        super(id, firstName, lastName, salary, corps);

        if (missions != null) {
            this.missions.addAll(Arrays.stream(missions).filter(e -> e != null).collect(Collectors.toList()));
        } else {
            this.missions = new ArrayList<>();
        }
    }

    @Override
    public void addMission(Mission missionState) {
        this.missions.add(missionState);
    }

    @Override
    public Collection<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("Missions:").append(System.lineSeparator());

        for (Mission mission : missions) {
            sb.append("  ").append(mission.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
