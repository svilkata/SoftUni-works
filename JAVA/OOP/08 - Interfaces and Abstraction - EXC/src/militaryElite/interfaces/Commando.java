package militaryElite.interfaces;

import militaryElite.models.Mission;

import java.util.Collection;

public interface Commando {
    void addMission(Mission missionState);
    Collection<Mission> getMissions();
}
