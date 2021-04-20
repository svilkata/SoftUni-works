package commandPattern;

import commandPattern.controllers.TurnOnCommand;
import commandPattern.factories.CommandFactory;
import commandPattern.interfaces.Command;
import commandPattern.interfaces.Invoker;

import java.util.HashMap;
import java.util.Map;

public class Remote implements Invoker {
    private Map<String, Command> commands;
    private CommandFactory commandFactory;

    public Remote(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.setCommands();
    }

    private void setCommands() {
        this.commands = this.commandFactory.createCommands();
    }

    @Override
    public void invoke(String name) {
        this.commands.get(name).execute();

    }
}
