package football;

import static football.Validator.checkIfValidName;
import static football.Validator.checkIfValidStats;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;


    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);

    }

    private void setName(String name) {
        checkIfValidName(name);
        this.name = name;
    }

    private void setEndurance(int endurance) {
        checkIfValidStats(endurance, "Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        checkIfValidStats(endurance, "Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        checkIfValidStats(endurance, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        checkIfValidStats(endurance, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        checkIfValidStats(endurance, "Shooting");
        this.shooting = shooting;
    }

    public String getName() {
        return this.name;
    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }
}
