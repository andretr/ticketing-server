package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriorityRepository extends JpaRepository<Priority, UUID> {

    @Query("SELECT p " +
            "FROM Priority p, Category c, Tenant t " +
            "WHERE p.categoryId = c.categoryId " +
            "AND c.tenantId = t.tenantId " +
            "AND c.tenantId = :tenantId " +
            "AND t.hiddenTenant is false ")
    List<Priority> findAllByTenantId(UUID tenantId);

    @Query("SELECT p " +
            "FROM Priority p, Category c, Tenant t " +
            "WHERE p.categoryId = c.categoryId " +
            "AND c.tenantId = t.tenantId " +
            "AND c.tenantId = :tenantId " +
            "AND t.hiddenTenant is false " +
            "AND p.priorityId = :priorityId")
    Optional<Priority> getByTenantIdAndPriorityId(UUID tenantId, UUID priorityId);

}