import java.io.*;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteToFile {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\svilk\\OneDrive\\Soft Engineer\\JAVA\\Advanced\\prepare - May 2020\\09 - Streams, files and directories - LAB\\input.txt";

        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        Scanner sc = new Scanner(inputStream);

        StringBuilder builder = new StringBuilder();
        String line = sc.nextLine();

        while (line != null) {
            builder.append(line.replaceAll("[,.!?]", "")).append(System.lineSeparator());
            try {
                line = sc.nextLine();
            } catch (NoSuchElementException ex) {
                inputStream.close();
                break;
            }
        }

        String string = builder.toString();

        FileOutputStream outputStream = new FileOutputStream("output.txt");
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(string);
        printWriter.flush();


    }
}
