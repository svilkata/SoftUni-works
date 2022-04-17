package com.example.json_ex.productsshop.repositories;

import com.example.json_ex.productsshop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems AS pr" +
            " WHERE pr.buyer IS NOT NULL")
    List<User> findAllWithSoldProducts();


    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems AS pr" +
            " WHERE pr.buyer IS NOT NULL" +
            " ORDER BY size(u.sellingItems) DESC, u.lastName ASC")
    List<User> findAllWithSoldProductsOrderByCount();
}
