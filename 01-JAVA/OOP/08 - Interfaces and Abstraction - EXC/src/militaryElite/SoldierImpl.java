package militaryElite;

import militaryElite.interfaces.Soldier;

public abstract class SoldierImpl implements Soldier {
    private int id;
    private String firstName;
    private String lastName;

    protected SoldierImpl(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %d",
                this.firstName, this.lastName, this.id);
    }
}
