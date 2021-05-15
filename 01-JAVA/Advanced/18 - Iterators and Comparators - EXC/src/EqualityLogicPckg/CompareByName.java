package EqualityLogicPckg;



import java.util.Comparator;

public class CompareByName implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int result = Integer.compare(o1.getName().length(), (o2.getName().length()));


        return result;
    }
}
