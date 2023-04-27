package com.ridebeep.bdipticketingserver.service;

import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> returnAllCategories(UUID tenantId) {
        return categoryRepository.findAllByHiddenTenantIsFalse();
    }

    @Override
    public Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId) {
        return categoryRepository.getByTenantIdAndCategoryId(tenantId, categoryId);
    }

    @Override
    public Category addCategory(UUID tenantId, Category newCategory) throws IllegalArgumentException{
        validateTenantIdMatchUrl(tenantId, newCategory);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(UUID tenantId, UUID categoryId, Category updatedCategory) {

        validateTenantIdMatchUrl(tenantId, updatedCategory);
        validateCategoryIdMatchUrl(categoryId, updatedCategory);

        Category category = categoryRepository
                .getByTenantIdAndCategoryId(tenantId, categoryId)
                .map(c  -> {
                    updatedCategory.setCreated(c.getCreated());
                    return updatedCategory;
                })
                .orElseThrow(() -> new IllegalArgumentException("Could not find category with id " + categoryId + " in tenant " + tenantId));

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID tenantId, UUID categoryId) {

        Category category = categoryRepository
                .getByTenantIdAndCategoryId(tenantId, categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find category with id " + categoryId + " in tenant " + tenantId));

        categoryRepository.delete(category);
    }


    private static void validateTenantIdMatchUrl(UUID tenantId, Category category) {
        if (!category.getTenantId().equals(tenantId)) {
            log.info("tenantId must match url");
            throw new IllegalArgumentException("tenantId must match request url");
        }
    }

    private static void validateCategoryIdMatchUrl(UUID categoryId, Category category) {
        if (!category.getCategoryId().equals(categoryId)) {
            log.info("categoryId must match url");
            throw new IllegalArgumentException("categoryId must match request url");
        }
    }
}
