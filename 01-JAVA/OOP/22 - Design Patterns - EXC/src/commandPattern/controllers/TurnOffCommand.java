package commandPattern.controllers;

import commandPattern.interfaces.Command;
import commandPattern.model.Television;

public class TurnOffCommand extends BaseCommand {
    public TurnOffCommand(Television television) {
        super(television);
    }

    @Override
    public void execute() {
        this.television.turnOff();
    }
}
