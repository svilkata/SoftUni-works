package func;

public class Purity {
    //pure function - при един и същи input връщат един и същи output - referential transparency
//    An expression is called referentially transparent if it can be replaced with its corresponding value without changing the program's behavior.
    public int sum(int a, int b) {
        return a + b;
    }

    int requestCount = 0;

    //non-pure function - Модифицира някакъв state
    public void incRequest(int count) {
        requestCount += count;
    }

    //constant
    public int get(){
        return 10;
    }

    //не връща резултат, и е безполезна
    public void useless(int a) {
        int b = a + 1;
    }
}
