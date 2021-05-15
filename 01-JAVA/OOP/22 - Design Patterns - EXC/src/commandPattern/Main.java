package commandPattern;

import commandPattern.factories.CommandFactory;
import commandPattern.factories.RemoteCommandFactory;
import commandPattern.interfaces.Invoker;
import commandPattern.model.SmartTV;
import commandPattern.model.Television;

public class Main {
    public static void main(String[] args) {
        Television television = new SmartTV();

        CommandFactory factory = new RemoteCommandFactory(television);
        Invoker remote = new Remote(factory);

        remote.invoke("TurnOn");
        remote.invoke("TurnOff");
        remote.invoke("VolumeUP");
    }
}
