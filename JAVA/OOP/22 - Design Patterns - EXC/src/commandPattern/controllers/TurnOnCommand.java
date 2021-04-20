package commandPattern.controllers;

import commandPattern.interfaces.Command;
import commandPattern.model.Television;

public class TurnOnCommand extends BaseCommand {
    public TurnOnCommand(Television television) {
        super(television);
    }

    @Override
    public void execute() {
        this.television.turnOn();

    }
}
