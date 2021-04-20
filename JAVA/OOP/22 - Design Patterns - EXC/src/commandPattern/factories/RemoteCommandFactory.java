package commandPattern.factories;

import commandPattern.controllers.TurnOffCommand;
import commandPattern.controllers.TurnOnCommand;
import commandPattern.controllers.VolumeUpCommand;
import commandPattern.interfaces.Command;
import commandPattern.model.Television;

import java.util.Map;

public class RemoteCommandFactory implements CommandFactory{
    private Television television;

    public RemoteCommandFactory(Television television) {
        this.television = television;
    }

    @Override
    public Map<String, Command> createCommands() {
        return Map.of(
                "TurnOn", new TurnOnCommand(this.television),
                "TurnOff", new TurnOffCommand(this.television),
                "VolumeUP", new VolumeUpCommand(this.television)
        );
    }
}
