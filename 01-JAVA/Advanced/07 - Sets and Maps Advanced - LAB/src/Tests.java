import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Tests {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> first = new HashSet<>();
        Set<String> second = new HashSet<>();

        first.add("First");second.add("First");
        first.add("Second");second.add("Second");
        first.add("Third");

        first.retainAll(second);

        for (String s : first) {
            System.out.println(s);
        }


    }
}
