package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u " +
            "FROM User u, Tenant t " +
            "WHERE u.userId = :userId " +
            "AND t.tenantId = :tenantId " +
            "AND u.tenantId = t.tenantId " +
            "AND t.hiddenTenant is false ")
    Optional<User> getByTenantIdAndUserId(UUID tenantId, UUID userId);
}