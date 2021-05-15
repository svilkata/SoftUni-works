import java.io.*;
import java.nio.file.Files;

public class CopyBytes {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\svilk\\OneDrive\\Soft Engineer\\JAVA\\Advanced\\prepare - May 2020\\09 - Streams, files and directories - LAB\\input.txt";
        File file = new File(path);
        byte[] bytes = Files.readAllBytes(file.toPath());

        Writer writer = new FileWriter("text-as-bytes.txt"); //директно пише във файла
        for (byte b : bytes) {
            String out = String.valueOf(b);
            if (b == 32) {
                out = " ";
            } else if (b == 10) {
                out = System.lineSeparator();
            }
            writer.write(out); //директно пише във файла
        }

        writer.flush();
    }
}
