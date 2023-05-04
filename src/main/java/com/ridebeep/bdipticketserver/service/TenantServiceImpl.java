package com.ridebeep.bdipticketserver.service;

import com.ridebeep.bdipticketserver.model.Tenant;
import com.ridebeep.bdipticketserver.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public List<Tenant> returnAllTenants() {
        return tenantRepository.findAllByHiddenTenantIsFalse();
    }

    @Override
    public List<UUID> returnAllTenantIds() {
        return tenantRepository.findAllNonHiddenTenantIds();
    }

    @Override
    public Optional<Tenant> returnTenantById(UUID tenantId) {
        return tenantRepository.findByTenantIdAndHiddenTenantIsFalse(tenantId);
    }

    @Override
    public Optional<Tenant> getTenantById(UUID tenantId) {
        return Optional.empty();
    }

    @Override
    public List<Tenant> returnAllTenantsbyIds(List<UUID> tenantIds) {
        return new ArrayList<>();
    }
}
