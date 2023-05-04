package com.ridebeep.bdipticketserver.repository;

import com.ridebeep.bdipticketserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c " +
            "FROM Category c, Tenant t " +
            "WHERE c.tenantId = t.tenantId " +
            "AND t.tenantId = :tenantId " +
            "AND t.hiddenTenant is false ")
    List<Category> findAllByTenant(UUID tenantId);

    @Query("SELECT c " +
            "FROM Category c, Tenant t " +
            "WHERE c.tenantId = t.tenantId " +
            "AND t.tenantId = :tenantId " +
            "AND t.hiddenTenant is false " +
            "AND c.categoryId = :categoryId ")
    Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId);
}