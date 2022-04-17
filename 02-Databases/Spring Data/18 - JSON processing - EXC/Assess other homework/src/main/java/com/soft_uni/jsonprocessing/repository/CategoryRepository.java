package com.soft_uni.jsonprocessing.repository;

import com.soft_uni.jsonprocessing.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.name AS category," +
            "   count(pc.product_id) AS products_count," +
            "   avg(p.price) AS average_price," +
            "    sum(p.price) AS total_revenue" +
            " FROM json_processing.categories AS c" +
            " JOIN json_processing.products_categories pc on c.id = pc.categories_id" +
            " JOIN json_processing.products AS p" +
            " ON p.id = pc.product_id" +
            " GROUP BY c.id", nativeQuery = true)
    List<Object[]> findAllByProductCount();
}
