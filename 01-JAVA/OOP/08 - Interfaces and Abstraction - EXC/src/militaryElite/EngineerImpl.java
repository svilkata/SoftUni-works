package militaryElite;

import militaryElite.enums.Corps;
import militaryElite.interfaces.Engineer;
import militaryElite.interfaces.Soldier;
import militaryElite.models.Repair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps, Repair... repairs) {
        super(id, firstName, lastName, salary, corps);

        if (repairs != null) {
            this.repairs.addAll(Arrays.stream(repairs).filter(e -> e != null).collect(Collectors.toList()));
        } else {
            this.repairs = new ArrayList<>();
        }
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public Collection<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("Repairs:").append(System.lineSeparator());

        for (Repair repair : repairs) {
            sb.append("  ").append(repair.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
