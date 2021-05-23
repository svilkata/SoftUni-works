import java.io.File;

public class PrintFiles_Directories {
    public static void main(String[] args) {
        File folder = new File("D:\\Video\\Figuri_tanci_uroci\\2014");

        printAllFilesInAllFolders(folder);
    }

    private static void printAllFilesInAllFolders(File folder) {
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length == 0) {
            return;
        }
        boolean isDirectoryPresent = false;

        for (File listOfFile : listOfFiles) {
            if (!listOfFile.isDirectory()) {
                System.out.println(listOfFile.getName());
            } else if (listOfFile.isDirectory()){
                isDirectoryPresent = true;
                printAllFilesInAllFolders(listOfFile);
            }
        }

        if (!isDirectoryPresent) {
            return;
        }

    }
}
