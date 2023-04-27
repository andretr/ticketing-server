package com.ridebeep.bdipticketingserver.service;

import com.ridebeep.bdipticketingserver.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    List<Category> returnAllCategories(UUID tenantId);

    Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId);

    Category addCategory(UUID tenantId, Category newCategory) throws IllegalArgumentException;

    Category updateCategory(UUID tenantId, UUID categoryId, Category updatedCategory) throws IllegalArgumentException;

    void deleteCategory(UUID tenantId, UUID categoryId) throws IllegalArgumentException;
}
