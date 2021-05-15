import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GetFolderSize {

    public static void main(String[] args) {

        File file = new File("src/resources/Exercises Resources");
        System.out.println(file.length());

    }
}
