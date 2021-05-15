package barracksWars.core.commands;

import barracksWars.interfaces.Inject;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import javax.naming.OperationNotSupportedException;

public class Retire extends Command {
    @Inject private Repository repository;

    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String typeOfUnit = this.getData()[1];
        String result;
        try {
            this.repository.removeUnit(typeOfUnit);
            result = typeOfUnit + " retired!";
        } catch (OperationNotSupportedException e) {
            result = e.getMessage();
        }

        return result;
    }
}
