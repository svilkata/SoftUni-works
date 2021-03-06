import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(13);    numbers.add(42);
        numbers.add(69);    numbers.add(73);

        ListIterator<Integer> iterator = numbers.listIterator();

        while (iterator.hasNext()){
            if(iterator.hasPrevious()) {}
            System.out.println(iterator.previous());
            System.out.println(iterator.previousIndex());
            iterator.add(5);
        }
    }
}
