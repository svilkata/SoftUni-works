import java.util.Scanner;

public class RepeatString {

    static String repeatString(String s, int repeatCount){
        String[] repeatArr = new String[repeatCount];
        for (int i = 0; i < repeatArr.length ; i++) {
            repeatArr[i] = s;
        }

        return String.join("", repeatArr);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int repeat = Integer.parseInt(sc.nextLine());

        System.out.println(repeatString(s, repeat));


    }
}
