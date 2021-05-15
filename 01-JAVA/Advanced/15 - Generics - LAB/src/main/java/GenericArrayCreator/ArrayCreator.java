package GenericArrayCreator;

import java.lang.reflect.Array;
import java.util.Objects;

public class ArrayCreator<T> {
    public static <T> T[] create(int length, T item) {
//        return (T[])new Object[length];
        return (T[]) Array.newInstance(item.getClass(), length);
    }

    public static<T> T[] create(Class<T> claaz, int length, T item){
        return (T[]) Array.newInstance(claaz, length);
    }
}
