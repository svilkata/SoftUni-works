package commandPattern.controllers;

import commandPattern.interfaces.Command;
import commandPattern.model.Television;

public abstract class BaseCommand implements Command {
    protected Television television;

    public BaseCommand(Television television) {
        this.television = television;
    }
}
