package com.ridebeep.bdipticketingserver.service;

import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.model.User;
import com.ridebeep.bdipticketingserver.model.UserCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    List<Category> returnAllCategories(UUID tenantId);

    Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId);

    Category addCategory(UUID tenantId, Category newCategory);

    Category updateCategory(UUID tenantId, UUID categoryId, Category updatedCategory);

    void deleteCategory(UUID tenantId, UUID categoryId);

    List<User> getAllUsersByCategory(UUID tenantId, UUID categoryId);

    Optional<UserCategory> getUserCategory(UUID tenantId, UUID categoryId, UUID userId);

    UserCategory addUserCategory(UUID tenantId, UUID categoryId, UserCategory userCategory);

    void deleteUserFromCategory(UUID tenantId, UUID categoryId, UUID userId);
}
