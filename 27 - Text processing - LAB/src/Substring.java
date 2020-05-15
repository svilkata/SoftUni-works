import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String toRemove = sc.nextLine();
        String text = sc.nextLine();

        while (text.contains(toRemove)) {
//            int toRemoveindex = text.indexOf(toRemove);
//            int toRemoveLength = toRemove.length();
//
//            text = text.substring(0, toRemoveindex) +
//                    text.substring(toRemoveindex + toRemoveLength);
            text = text.replace(toRemove, "");
        }

        System.out.println(text);

    }
}
