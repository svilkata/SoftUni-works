package BookTask;

import java.util.Comparator;

public class CompareBooksByYearsDescending implements Comparator<Book> {
    @Override
    public int compare(Book first, Book second) {
        return Integer.compare(second.getYear(), first.getYear());
    }
}
