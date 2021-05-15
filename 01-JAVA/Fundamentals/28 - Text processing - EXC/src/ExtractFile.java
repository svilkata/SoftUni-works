import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String path = sc.nextLine();

        int slashIndex = path.lastIndexOf('\\');
        int pointIndex = path.lastIndexOf('.');

        String fileName = path.substring(slashIndex +1, pointIndex);
        String fileExtension = path.substring(pointIndex+1);

        System.out.println("File name: " + fileName);
        System.out.println("File extension: " + fileExtension);
    }
}
