import java.io.File;
import java.util.Arrays;

public class PrintFiles_Directories {
    public static void main(String[] args) {
        File folder = new File("D:\\Video\\Figuri_tanci_uroci\\2014");

        printAllFilesInAllFolders(folder);
    }

    private static void printAllFilesInAllFolders(File folder) {
        File[] listOfCurrentDirectories = folder.listFiles(file -> file.isDirectory());
        File[] listOfCurrentFilesToPrint = folder.listFiles(file -> !file.isDirectory());

        for (File currentFileToPrint : listOfCurrentFilesToPrint) {
            System.out.println(currentFileToPrint.getName());
        }

        if (listOfCurrentDirectories.length != 0) {
            for (File currentDIrectory : listOfCurrentDirectories) {
                printAllFilesInAllFolders(currentDIrectory);
            }
        } else {
            return;
        }
    }
}
