package commandPattern;

import java.util.ArrayList;
import java.util.List;

public class ModifyPrice {
    private List<Command> commands;

    public ModifyPrice() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void invoke() {
        this.commands.forEach(c -> System.out.println(c.execute()));
    }
}
