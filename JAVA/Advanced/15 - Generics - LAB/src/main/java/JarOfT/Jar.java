package JarOfT;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<Type> {
    private Deque<Type> stack;

    public Jar() {
        this.stack = new ArrayDeque<>();
    }

    public void add(Type element){
        this.stack.push(element);
    }

    public Type remove(){
        return this.stack.pop();
    }
}
