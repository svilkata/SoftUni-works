package springintro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springintro.model.entity.Author;
import springintro.model.entity.AuthorNamesWithTotalCopies;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findByFirstNameEndingWith(String endsWith);

//    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName, ' ', SUM(b.copies)), SUM(b.copies) AS  totalCopies" +
//            " FROM Author a" +
//            " JOIN a.books AS b" +
//            " GROUP BY b.author" +
//            " ORDER BY totalCopies DESC")
//    List<String> getWithTotalCopies();

        @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS  totalCopies" +
            " FROM Author a" +
            " JOIN a.books AS b" +
            " GROUP BY b.author" +
            " ORDER BY totalCopies DESC")
    List<AuthorNamesWithTotalCopies> getWithTotalCopies();
}
