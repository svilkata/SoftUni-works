import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToZipFiles {
    public static void main(String[] args) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("src/resources/textfiles.zip")));
        FileInputStream fis = new FileInputStream(new File("src/resources/words.txt"));
        int byteContainter;

        zos.putNextEntry(new ZipEntry("words.txt"));
        while ((byteContainter = fis.read()) != -1) {
            zos.write(byteContainter);
        }
        zos.closeEntry();

        zos.putNextEntry(new ZipEntry("text.txt"));
        fis = new FileInputStream(new File("src/resources/text.txt"));
        while ((byteContainter = fis.read()) != -1) {
            zos.write(byteContainter);
        }
        zos.closeEntry();

        zos.putNextEntry(new ZipEntry("input.txt"));
        fis = new FileInputStream(
                new File("src/resources/input.txt"));
        while ((byteContainter = fis.read()) != -1) {
            zos.write(byteContainter);
        }
        zos.closeEntry();

        zos.finish();
        zos.close();
    }
}
