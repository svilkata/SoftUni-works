package utils.helpOutFile;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper implements FileStorage {
    private File file;
    private long size;
    private String path;

    public FileHelper() {
        this.path = "out.txt";
        this.file = new File(this.path);
        this.size = 0;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void write(String text) {

        this.addToSize(text);
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(this.file.getPath()));
            StringBuilder builder = new StringBuilder();

            for (byte aByte : bytes) {
                builder.append(Character.valueOf((char) aByte));
            }

            builder.append(text);

            PrintWriter writer = new PrintWriter(this.file);
            writer.append(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public long getSize(){
        return this.size;
    }

    private void addToSize(String text) {
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (Character.isAlphabetic(symbol)) {
                this.size += symbol;
            }
        }
    }
}
