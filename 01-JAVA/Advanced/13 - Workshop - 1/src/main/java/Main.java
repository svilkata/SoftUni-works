import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        for (int i = 1; i <= 10; i++) {
            myStack.push(i);
        }

        myStack.forEach(e-> System.out.println(e));
    }
}
