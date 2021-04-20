package militaryElite;

import militaryElite.core.Engine;
import militaryElite.core.EngineImpl;
import militaryElite.interfaces.Private;
import militaryElite.utils.readers.ConsoleReader;
import militaryElite.utils.readers.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleReader();
        Engine engine = new EngineImpl(reader);
        engine.run();

    }
}
