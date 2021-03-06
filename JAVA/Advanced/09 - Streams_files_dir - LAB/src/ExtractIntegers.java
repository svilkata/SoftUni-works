import java.io.*;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String input = reader.readLine();

        PrintWriter writer = new PrintWriter("integer.csv");

//        while (sc.hasNext()) {
//            if (sc.hasNextInt()) {
//                int nextInt = sc.nextInt();
//                writer.println(nextInt);
//            }
//            sc.next();
//        }
//        writer.flush();
    }
}
