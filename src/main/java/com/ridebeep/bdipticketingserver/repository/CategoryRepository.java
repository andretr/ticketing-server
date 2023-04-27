package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c " +
            "FROM Category c, Tenant t " +
            "WHERE c.tenantId = t.tenantId " +
            "AND t.hiddenTenant is false ")
    List<Category> findAllByHiddenTenantIsFalse();

    @Query("SELECT c " +
            "FROM Category c, Tenant t " +
            "WHERE c.categoryId = :categoryId " +
            "AND c.tenantId = :tenantId " +
            "AND c.tenantId = t.tenantId " +
            "AND t.hiddenTenant is false ")
    Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId);
}