public class Main {
    public static void main(String[] args) {
        printVarArgs();
        printVarArgs("1", "2", "3");


    }

    public static <T> void printVarArgs(T... elements) {
        if (elements.length == 0) {
            System.out.println("No elements");
        } else {
            T[] arrList = elements;
            for (T element : arrList) {
                System.out.println(element);
            }
        }


    }
}
