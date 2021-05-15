package BookTask;

import java.util.Comparator;

public class CompareBooksByYearsAscending implements Comparator<Book> {
    @Override
    public int compare(Book first, Book second) {
        return Integer.compare(first.getYear(), second.getYear());
    }
}
