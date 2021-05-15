import java.io.*;

public class CopyPicture {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(new File("src\\QVNIMTE0MzQ0MzA4.jpg"));
        FileOutputStream outputStream = new FileOutputStream(new File("src\\destination.jpg"));

        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = fis.read(buffer)) > 0) {
            outputStream.write(buffer, 0, read);
        }


    }
}
