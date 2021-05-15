package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

//fight command
public class Fight extends Command{
    public Fight(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
