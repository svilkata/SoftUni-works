import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class NestedFolders {
    public static void main(String[] args) {
        File file = new File("Files-and-Streams");
        Deque<File> queue = new ArrayDeque<>();
        queue.offer(file);
        int count = 0;

        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] nestedFiles = current.listFiles();

            for (File f : nestedFiles) {
                if (f.isDirectory()) {
                    queue.offer(f);
                }
            }

            count++;
            System.out.println(current.getName());
        }

        System.out.println(count + " folders");
    }
}
