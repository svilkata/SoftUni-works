package func;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Higher-order function · takes one or more functions as arguments
public class HighOrder {
    public void exampleTakesParam(){
        List<String> students = new ArrayList<>();
        students.add("Student1");
        students.add("Student2");
        students.add("Student3");

        //sort e high-order фунцкия
        students.sort((a, b) -> a.compareTo(b));

        //функцията comp връща друга функция comp.reversed, която пак е Comparator
        Comparator<String> comp = (a, b) -> a.compareTo(b);
        comp.reversed();
    }
}
