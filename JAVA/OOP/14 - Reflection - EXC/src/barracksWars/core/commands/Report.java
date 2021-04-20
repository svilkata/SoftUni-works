package barracksWars.core.commands;

import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;

//report command
public class Report extends Command implements Executable {
    @Inject private Repository repository;

    public Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
