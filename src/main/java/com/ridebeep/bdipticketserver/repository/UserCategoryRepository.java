package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.User;
import com.ridebeep.bdipticketserver.model.UserCategory;
import com.ridebeep.bdipticketserver.model.id.UserCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserCategoryRepository extends JpaRepository<UserCategory, UserCategoryId> {

    @Query("SELECT u " +
            "FROM User u, Category c, UserCategory uc " +
            "WHERE u.userId = uc.userId " +
            "AND c.categoryId = uc.categoryId " +
            "AND c.categoryId = :categoryId")
    List<User> getAllUsersFromCategory(UUID categoryId);

    Optional<UserCategory> findUserCategoryByCategoryIdAndUserId(UUID categoryId, UUID userId);
}