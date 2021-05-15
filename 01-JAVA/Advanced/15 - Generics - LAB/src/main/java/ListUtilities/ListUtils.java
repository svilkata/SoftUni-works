package ListUtilities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {
    public static <T extends Comparable<T>> T getMax(List<T> collection){
        return collection.stream().max(T::compareTo)
                .orElse(null);
    }

    public static <T extends Comparable<T>> T getMin(List<T> collection){
        return collection.stream().min(T::compareTo).orElse(null);
    }

    public static <T extends Comparable<T>> void sort(List<T> collection){
        Collections.sort(collection);
    }

}
