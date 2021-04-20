package barracksWars.core;

import barracksWars.interfaces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private final static String PACKAGE_NAME = "barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        Executable executable = null;

        String command = getCorrectClassName(data[0]);
        try {
            Class clazz = Class.forName(PACKAGE_NAME + command);
            Constructor constructor = clazz.getDeclaredConstructor(String[].class);
            constructor.setAccessible(true);
            executable = (Executable) constructor.newInstance(new Object[]{data});
            populateDependencies(executable);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        }

        return executable;
    }

    private void populateDependencies(Executable executable) {
        Field[] exeFields = executable.getClass().getDeclaredFields();
        Field[] currentClazFields = this.getClass().getDeclaredFields();

        for (Field requiredField : exeFields) {
            Inject annotation = null;
            try {
                annotation = requiredField.getAnnotation(Inject.class);
            } catch (ClassCastException e){
                continue;
            }

            //if requiredField must be injected
            for (Field currentClazField : currentClazFields) {
                if (currentClazField.getType().equals(requiredField.getType())) {
                    requiredField.setAccessible(true);
                    try {
                        requiredField.set(executable, currentClazField.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }


    }

    private String getCorrectClassName(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(name.charAt(0)));
        sb.append(name.substring(1));

        return sb.toString();
    }
}
