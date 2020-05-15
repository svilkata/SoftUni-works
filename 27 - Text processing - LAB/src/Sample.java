import java.util.Scanner;

public class Sample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        sb.append("Hello Peter, how are you?");
        System.out.println(sb.charAt(1));



        sb.insert(11, " Ivanov");
        System.out.println(sb);  // Hello Peter Ivanov, how are you?


        sb.append("Hello Peter, how are you?");
        sb.replace(6, 11, "George");
        String text = sb.toString();
        System.out.println(text);


    }
}
