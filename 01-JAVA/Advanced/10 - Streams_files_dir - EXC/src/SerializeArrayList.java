import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeArrayList {
    public static void main(String[] args) throws IOException {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/resources/out.tx"));

//        objectInputStream.readObject()

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream("src/resources/list.ser"));

        for (Integer number : numbers) {
            objectOutputStream.write(number);
        }


    }
}
