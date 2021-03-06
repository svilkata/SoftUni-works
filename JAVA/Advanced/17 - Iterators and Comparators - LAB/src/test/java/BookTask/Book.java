package BookTask;

import java.util.*;
import java.util.function.Consumer;

public class Book implements Comparable<Book>{
    private String title;
    private int year;
    private List<String> authors;

    public List<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuhtors(authors);
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setYear(int year) {
        this.year = year;
    }

    private void setAuhtors(String... authors) {
        this.authors = Arrays.asList(authors);
    }

    @Override
    public String toString() {
        return "Book " + title + ", year=" + year +
                ", authors=" + authors;
    }

    @Override
    public int compareTo(Book other) {
        int result = this.title.compareTo(other.title);
        return result != 0 ? result : Integer.compare(this.year, other.year);
    }
}
