package com.ridebeep.bdipticketserver.service;

import com.ridebeep.bdipticketserver.model.Tenant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TenantService {

    List<Tenant> returnAllTenants();

    List<UUID> returnAllTenantIds();

    Optional<Tenant> returnTenantById(UUID tenantId);

    Optional<Tenant> getTenantById(UUID tenantId);

    List<Tenant> returnAllTenantsbyIds(List<UUID> tenantIds);
}
