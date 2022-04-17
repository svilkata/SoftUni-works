package com.soft_uni.jsonprocessing.repository;

import com.soft_uni.jsonprocessing.model.dto.UserDto.UserCountDto;
import com.soft_uni.jsonprocessing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT distinct u from User u " +
            " LEFT JOIN Product p ON u.id = p.seller.id" +
            " WHERE p.buyer IS NOT NULL " +
            "ORDER BY u.lastName, u.firstName")
    List<User> findAllUsersWithAtLeastOneProductSoldOrderedByLastNameThenFirstName();

    @Query(value = "SELECT distinct u FROM User u" +
            " LEFT JOIN Product p ON u.id = p.seller.id" +
            " WHERE p.buyer IS NOT NULL " +
            " ORDER BY size(u.soldProducts) DESC, u.lastName")
    List<User> findAllUsersWithAtLeastOneProductSoldOrderedByCountOfProductsDescTheLastName();



}
