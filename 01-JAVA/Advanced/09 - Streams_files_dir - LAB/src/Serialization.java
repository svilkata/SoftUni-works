import java.io.*;
import java.util.List;

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        List<Integer> list = List.of(13, 42, 43, 98, 73);
//        FileOutputStream fos = new FileOutputStream("ser.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(list);
//        oos.close();

        FileInputStream fis = new FileInputStream("ser.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Integer> result = (List<Integer>)ois.readObject(); //кастваме към Лист от Integer
        for (Integer r : result) {
            System.out.println(r);
        }


    }
}
