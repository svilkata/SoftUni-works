import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("C:\\Users\\svilk\\OneDrive\\Soft Engineer\\JAVA\\Advanced\\prepare - May 2020\\09 - Streams, files and directories - LAB\\input.txt");


        byte[] bytes = Files.readAllBytes(path);
        for (byte b : bytes) {
            System.out.print(Integer.toBinaryString(b) + " ");
        }

    }
}
