package militaryElite.core;

import militaryElite.*;
import militaryElite.enums.Corps;
import militaryElite.enums.MissionState;
import militaryElite.interfaces.LieutenantGeneral;
import militaryElite.interfaces.Private;
import militaryElite.interfaces.Soldier;
import militaryElite.models.Mission;
import militaryElite.models.MissionImpl;
import militaryElite.models.Repair;
import militaryElite.models.RepairImpl;
import militaryElite.utils.Validator;
import militaryElite.utils.readers.InputReader;

import java.util.ArrayList;
import java.util.List;

public class EngineImpl implements Engine {
    public static final String END_LINE = "End";
    private InputReader reader;

    public EngineImpl(InputReader reader) {
        this.reader = reader;
    }


    @Override
    public void run() {
        List<Soldier> privates = new ArrayList<>();

        String line = this.reader.readLine();
        while (!line.equals(END_LINE)) {
            String[] tokens = line.split("\\s+");
            String type = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];
            Private soldier = null;


            switch (type) {
                case "Private":
                    soldier = new PrivateImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));
                    break;
                case "LieutenantGeneral":
                    LieutenantGeneral lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, Double.parseDouble(tokens[4]));

                    for (int i = 5; i < tokens.length; i++) {
                        int wantedID = Integer.parseInt(tokens[i]);
                        Soldier firstSoldier = privates.stream().
                                filter(p -> p.getID() == wantedID).findFirst().orElse(null);

                        lieutenantGeneral.addPrivate(firstSoldier);
                    }

                    soldier = (Private) lieutenantGeneral;

                    break;
                case "Engineer":
                    try {
                        Validator.validateCorps(tokens[5]);
                        Repair[] repairs = new RepairImpl[(tokens.length - 6) / 2];

                        int index = 0;
                        for (int i = 6; i < tokens.length; i += 2) {
                            repairs[index++] = new RepairImpl(tokens[i], Integer.parseInt(tokens[i + 1]));
                        }

                        Corps corps = tokens[5].equals(Corps.AIRFORCE.getName()) ?
                                Corps.AIRFORCE : Corps.MARINE;
                        soldier = new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), corps, repairs);
                    } catch (IllegalArgumentException e) {
                        line = reader.readLine();
                        continue;
                    }
                    break;
                case "Commando":
                    try {
                        Validator.validateCorps(tokens[5]);
                        Mission[] missions = new Mission[(tokens.length - 6) / 2];

                        int index = 0;
                        for (int i = 6; i < tokens.length; i += 2) {
                            boolean isValid = Validator.validateMissionState(tokens[i + 1]);
                            if (isValid) {
                                MissionState state = tokens[i + 1].equals(MissionState.IN_PROGRESS.getState()) ?
                                        MissionState.IN_PROGRESS : MissionState.FINISHED;
                                missions[index++] = new MissionImpl(tokens[i], state);
                            }
                        }

                        Corps corps = tokens[5].equals(Corps.AIRFORCE.getName()) ?
                                Corps.AIRFORCE : Corps.MARINE;
                        soldier = new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), corps, missions);
                    } catch (IllegalArgumentException e) {
                        line = reader.readLine();
                        continue;
                    }
                    break;
                case "Spy":
                    soldier = (Private) new SpyImpl(id, firstName, lastName, tokens[4]);
                    break;
                default:
//                    throw new IllegalArgumentException("Invalid input type!");
//                    break;
            }

            if (soldier != null) {
                privates.add(soldier);
            }
            line = this.reader.readLine();
        }

        for (Soldier aPrivate : privates) {
            System.out.println(aPrivate.toString());
        }
    }
}
