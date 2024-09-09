package func;

import java.util.function.Function;

public class CurriFunctionDemo {
    public int multiply(int a, int b){
        return a * b;
    }

    Function<Integer, Function<Integer, Integer>> currMulti = u -> v -> u * v;

    public void test(){
        int res1 = multiply(2, 3);
        int res2 = currMulti.apply(2).apply(3);
    }
}
