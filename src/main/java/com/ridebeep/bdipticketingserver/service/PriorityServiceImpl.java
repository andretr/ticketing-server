package com.ridebeep.bdipticketingserver.service;

import com.ridebeep.bdipticketingserver.exceptions.InvalidArgumentException;
import com.ridebeep.bdipticketingserver.exceptions.ResourceAlreadyExistsException;
import com.ridebeep.bdipticketingserver.exceptions.ResourceNotFoundException;
import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.model.Priority;
import com.ridebeep.bdipticketingserver.repository.CategoryRepository;
import com.ridebeep.bdipticketingserver.repository.PriorityRepository;
import com.ridebeep.bdipticketingserver.utils.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriorityServiceImpl implements PriorityService {
    
    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Priority> returnAllPriorities(UUID tenantId) {
        return priorityRepository.findAllByTenantId(tenantId);
    }

    @Override
    public Optional<Priority> getByTenantIdAndPriorityId(UUID tenantId, UUID priorityId) {
        return priorityRepository.getByTenantIdAndPriorityId(tenantId, priorityId);
    }

    //TODO validate TimeUnits
    @Override
    public Priority addPriority(UUID tenantId, Priority newPriority){

        validateCategoryBelongsToTenant(tenantId, newPriority.getCategoryId());

        if (newPriority.getPriorityId() == null) {
            newPriority.setPriorityId(UUID.randomUUID());
        } else {
            if(getByTenantIdAndPriorityId(tenantId, newPriority.getPriorityId()).isPresent())
                throw new ResourceAlreadyExistsException(MessagesUtil.priorityIdAlreadyExistsError(newPriority.getPriorityId()));
        }

        return priorityRepository.save(newPriority);
    }

    @Override
    public Priority updatePriority(UUID tenantId, UUID priorityId, Priority updatedPriority) {

        validateCategoryBelongsToTenant(tenantId, updatedPriority.getCategoryId());
        validatePriorityIdMatchUrl(priorityId, updatedPriority.getPriorityId());

        Priority priority = priorityRepository
                .getByTenantIdAndPriorityId(tenantId, priorityId)
                .map(c  -> {
                    updatedPriority.setCreated(c.getCreated());
                    return updatedPriority;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessagesUtil.priorityTenantError(priorityId, tenantId)));

        return priorityRepository.save(priority);
    }

    @Override
    public void deletePriority(UUID tenantId, UUID priorityId) {

        Priority priority =
                getByTenantIdAndPriorityId(tenantId, priorityId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(MessagesUtil.priorityTenantError(priorityId, tenantId)));

        priorityRepository.delete(priority);
    }


    private static void validatePriorityIdMatchUrl(UUID priorityIdFromUrlPath, UUID priorityId) {
        if (!priorityIdFromUrlPath.equals(priorityId)) {
            throw new InvalidArgumentException(MessagesUtil.priorityIdUrlError(priorityId));
        }
    }
    private void validateCategoryBelongsToTenant(UUID tenantIdFromUrlPath, UUID categoryId) {

        Optional<Category> category = categoryRepository.getByTenantIdAndCategoryId(tenantIdFromUrlPath, categoryId);
        if (category.isEmpty()) {
            throw new InvalidArgumentException(MessagesUtil.categoryTenantError(categoryId, tenantIdFromUrlPath));
        }
    }

}
