import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteEveryThirdLine {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        List<String> listLines = in.lines().collect(Collectors.toList());

        PrintWriter out = new PrintWriter(new FileWriter("anotherFile.txt"));

        for (int i = 0; i < listLines.size(); i++) {
            if ((i + 1) % 3 == 0) {
                out.append(listLines.get(i)).append(System.lineSeparator()) ;
            }
        }

        out.flush();




    }
}
