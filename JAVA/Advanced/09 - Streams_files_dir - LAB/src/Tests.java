import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tests {
    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";



        File file = new File("new-file.txt"); //create new file
        File directory = new File("newDir"); //create new folder

        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
