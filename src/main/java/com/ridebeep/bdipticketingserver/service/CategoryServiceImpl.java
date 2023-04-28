package com.ridebeep.bdipticketingserver.service;

import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.model.User;
import com.ridebeep.bdipticketingserver.model.UserCategory;
import com.ridebeep.bdipticketingserver.repository.CategoryRepository;
import com.ridebeep.bdipticketingserver.repository.UserCategoryRepository;
import com.ridebeep.bdipticketingserver.repository.UserRepository;
import com.ridebeep.bdipticketingserver.utils.MessagesUtil;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCategoryRepository userCategoryRepository;

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
        validateTenantIdMatchUrl(tenantId, newCategory.getTenantId());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(UUID tenantId, UUID categoryId, Category updatedCategory) {

        validateTenantIdMatchUrl(tenantId, updatedCategory.getTenantId());
        validateCategoryIdMatchUrl(categoryId, updatedCategory.getCategoryId());

        Category category = categoryRepository
                .getByTenantIdAndCategoryId(tenantId, categoryId)
                .map(c  -> {
                    updatedCategory.setCreated(c.getCreated());
                    return updatedCategory;
                })
                .orElseThrow(() ->
                    new IllegalArgumentException(MessagesUtil.tenantCategoryError(categoryId, tenantId)));

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID tenantId, UUID categoryId) {

        Category category =
                getByTenantIdAndCategoryId(tenantId, categoryId)
                .orElseThrow(() ->
                        new IllegalArgumentException(MessagesUtil.tenantCategoryError(categoryId, tenantId)));

        categoryRepository.delete(category);
    }

    @Override
    public List<User> getAllUsersByCategory(UUID tenantId, UUID categoryId) {

        Optional<Category> category = categoryRepository.getByTenantIdAndCategoryId(tenantId, categoryId);
        if(category.isEmpty())
            throw new IllegalArgumentException(MessagesUtil.tenantCategoryError(categoryId, tenantId));

        return userCategoryRepository.getAllUsersFromCategory(categoryId);
    }

    @Override
    public Optional<UserCategory> getUserCategory(UUID tenantId, UUID categoryId, UUID userId) {

        validateTenantForUserCategory(tenantId, new UserCategory(categoryId, userId));
        return userCategoryRepository.findUserCategoryByCategoryIdAndUserId(categoryId, userId);
    }

    @Override
    public UserCategory addUserCategory(UUID tenantId, UUID categoryId, UserCategory userCategory) {

        validateCategoryIdMatchUrl(categoryId, userCategory.getCategoryId());
        validateTenantForUserCategory(tenantId, new UserCategory(userCategory.getCategoryId(), userCategory.getUserId()));
        return userCategoryRepository.save(userCategory);
    }

    @Override
    public void deleteUserFromCategory(UUID tenantId, UUID categoryId, UUID userId) {

        UserCategory userCategory =
                getUserCategory(tenantId,categoryId,userId)
                .orElseThrow(() ->
                        new IllegalArgumentException(MessagesUtil.userCategoryError(categoryId, userId)));

        userCategoryRepository.delete(userCategory);
    }

    //Validation Methods



    private static void validateTenantIdMatchUrl(UUID tenantIdFromUrlPath, UUID tenantId) {

        if (!tenantIdFromUrlPath.equals(tenantId)) {
            throw new IllegalArgumentException("tenantId must match request url");
        }
    }

    private static void validateCategoryIdMatchUrl(UUID categoryIdFromUrlPath, UUID categoryIdy) {
        if (!categoryIdFromUrlPath.equals(categoryIdy)) {
            log.info("categoryId must match url {} {} {} {}", 120, 12, 14, 0);
            throw new IllegalArgumentException("categoryId must match request url");
        }
    }

    private void validateTenantForUserCategory(UUID tenantId, UserCategory userCategory) {

        Optional<Category> category = categoryRepository.getByTenantIdAndCategoryId(tenantId, userCategory.getCategoryId());
        if(category.isEmpty())
            throw  new IllegalArgumentException("Could not find category with id " + userCategory.getCategoryId() + " in tenant " + tenantId);

        Optional<User> user = userRepository.getByTenantIdAndUserId(tenantId, userCategory.getUserId());
        if(user.isEmpty())
            throw  new IllegalArgumentException("Could not find user with id " + userCategory.getUserId() + " in tenant " + tenantId);
    }
}
