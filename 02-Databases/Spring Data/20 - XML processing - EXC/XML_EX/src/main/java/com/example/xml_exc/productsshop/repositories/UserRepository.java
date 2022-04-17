package com.example.xml_exc.productsshop.repositories;

import com.example.xml_exc.productsshop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u" +
            " JOIN u.sellingItems AS pr" +
            " WHERE pr.buyer IS NOT NULL" +
            " ORDER BY u.lastName ASC, u.firstName ASC")
    List<User> findAllWithSoldProducts();
}
