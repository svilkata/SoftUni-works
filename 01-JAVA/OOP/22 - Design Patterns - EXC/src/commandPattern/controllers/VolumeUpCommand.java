package commandPattern.controllers;

import commandPattern.model.Television;

public class VolumeUpCommand extends BaseCommand {
    public VolumeUpCommand(Television television) {
        super(television);
    }

    @Override
    public void execute() {
        television.volumeUp();
    }
}
