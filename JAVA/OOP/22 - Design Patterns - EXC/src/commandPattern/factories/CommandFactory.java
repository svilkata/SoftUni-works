package commandPattern.factories;

import commandPattern.interfaces.Command;

import java.util.Map;

public interface CommandFactory {
    Map<String, Command> createCommands();
}
