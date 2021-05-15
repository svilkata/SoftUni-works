package BookTask;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        String[] authors = new String[]{"A", "Terry Pratchet", "Tolkein", "Ivan Vazov"};

        Book bookOne = new Book("Animal Farm", 2003, "A", "Terry Pratchet", "Tolkein", "Ivan Vazov");
        Book bookTwo = new Book("The Documents in the Case", 2002);
        Book bookThree = new Book("The Documents in the Case", 1930, "Dorothy Sayers", "Robert Eustace");

        Set<Book> bookSet = new TreeSet<>(new BookComparator());

        List<Book> books = new ArrayList<>(Arrays.asList(bookOne, bookTwo, bookThree));
        books.sort(new BookComparator());

        for (Book book : books) {
            System.out.println(book);
        }

//        Arrays.stream(books).sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getYear)).forEach(System.out::println);

//        Arrays.stream(books).sorted((f, s) -> f.getTitle().compareTo(s.getTitle())).forEach(System.out::println);

//        CompareBooksByYearsAscending compareBooksByYearsAscending = new CompareBooksByYearsAscending();
//        Arrays.stream(books).sorted(compareBooksByYearsAscending::compare).forEach(System.out::println);
//
//
//        Arrays.stream(books).sorted((f, s) -> Integer.compare(s.getYear(), f.getYear())).forEach(System.out::println);
//        Arrays.stream(books).sorted((f, s) -> Integer.compare(s.getAuthors().size(), f.getAuthors().size())).forEach(System.out::println);

//        Library library = new Library(books);

//        System.out.println("Lib iter: ");
//        for (Book book : library) {
//            System.out.println(book.toString());
//        }
//
//        System.out.println("Normal for loop");
//        for (int i = 0; i < books.length; i++) {
//            System.out.println(books[i].toString());
//        }


    }

}
