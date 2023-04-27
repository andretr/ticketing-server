package com.ridebeep.bdipticketingserver.repository;

import com.ridebeep.bdipticketingserver.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    List<Tenant> findAllByHiddenTenantIsFalse();
    @Query("SELECT tenant.tenantId FROM Tenant tenant WHERE tenant.hiddenTenant = false")
    List<UUID> findAllNonHiddenTenantIds();
    Optional<Tenant> findByTenantIdAndHiddenTenantIsFalse(UUID tenantId);

}