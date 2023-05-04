package com.ridebeep.bdipticketserver.service;

import com.ridebeep.bdipticketserver.model.Priority;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriorityService {

    List<Priority> returnAllPriorities(UUID tenantId);

    Optional<Priority> getByTenantIdAndPriorityId(UUID tenantId, UUID priorityId);

    Priority addPriority(UUID tenantId, Priority newPriority);

    Priority updatePriority(UUID tenantId, UUID priorityId, Priority updatedPriority);

    void deletePriority(UUID tenantId, UUID priorityId);
}
