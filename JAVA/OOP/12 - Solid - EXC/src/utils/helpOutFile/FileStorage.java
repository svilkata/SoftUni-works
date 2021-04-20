package utils.helpOutFile;

import java.io.File;

public interface FileStorage {
    void setPath(String path);
    void setFile(File file);
    void write(String text);
    long getSize();
}
