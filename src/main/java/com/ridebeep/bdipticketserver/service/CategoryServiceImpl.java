package com.ridebeep.bdipticketserver.service;

import com.ridebeep.bdipticketserver.exceptions.InvalidArgumentException;
import com.ridebeep.bdipticketserver.exceptions.ResourceAlreadyExistsException;
import com.ridebeep.bdipticketserver.exceptions.ResourceNotFoundException;
import com.ridebeep.bdipticketserver.model.Category;
import com.ridebeep.bdipticketserver.model.User;
import com.ridebeep.bdipticketserver.model.UserCategory;
import com.ridebeep.bdipticketserver.repository.CategoryRepository;
import com.ridebeep.bdipticketserver.repository.UserCategoryRepository;
import com.ridebeep.bdipticketserver.repository.UserRepository;
import com.ridebeep.bdipticketserver.utils.MessagesUtil;
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
        return categoryRepository.findAllByTenant(tenantId);
    }

    @Override
    public Optional<Category> getByTenantIdAndCategoryId(UUID tenantId, UUID categoryId) {
        return categoryRepository.getByTenantIdAndCategoryId(tenantId, categoryId);
    }

    @Override
    public Category addCategory(UUID tenantId, Category newCategory){

        validateTenantIdMatchUrl(tenantId, newCategory.getTenantId());

        if (newCategory.getCategoryId() == null) {
            newCategory.setCategoryId(UUID.randomUUID());
        } else {
            if(getByTenantIdAndCategoryId(tenantId, newCategory.getCategoryId()).isPresent())
               throw new ResourceAlreadyExistsException(MessagesUtil.categoryIdAlreadyExistsError(newCategory.getCategoryId()));
        }

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
                    new ResourceNotFoundException(MessagesUtil.categoryTenantError(categoryId, tenantId)));

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID tenantId, UUID categoryId) {

        Category category =
                getByTenantIdAndCategoryId(tenantId, categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessagesUtil.categoryTenantError(categoryId, tenantId)));

        categoryRepository.delete(category);
    }

    @Override
    public List<User> getAllUsersByCategory(UUID tenantId, UUID categoryId) {

        Optional<Category> category = categoryRepository.getByTenantIdAndCategoryId(tenantId, categoryId);
        if(category.isEmpty())
            throw new ResourceNotFoundException(MessagesUtil.categoryTenantError(categoryId, tenantId));

        return userCategoryRepository.getAllUsersFromCategory(categoryId);
    }

    @Override
    public Optional<UserCategory> getUserCategory(UUID tenantId, UUID categoryId, UUID userId) {

        validateUserCategoryBelongToTenant(tenantId, new UserCategory(categoryId, userId));
        return userCategoryRepository.findUserCategoryByCategoryIdAndUserId(categoryId, userId);
    }

    @Override
    public UserCategory addUserCategory(UUID tenantId, UUID categoryId, UserCategory userCategory) {

        validateCategoryIdMatchUrl(categoryId, userCategory.getCategoryId());
        validateUserCategoryBelongToTenant(tenantId, new UserCategory(userCategory.getCategoryId(), userCategory.getUserId()));
        return userCategoryRepository.save(userCategory);
    }

    @Override
    public void deleteUserFromCategory(UUID tenantId, UUID categoryId, UUID userId) {

        UserCategory userCategory =
                getUserCategory(tenantId,categoryId,userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessagesUtil.userCategoryError(categoryId, userId)));

        userCategoryRepository.delete(userCategory);
    }

    //Calidation Methods
    private static void validateTenantIdMatchUrl(UUID tenantIdFromUrlPath, UUID tenantId) {

        if (!tenantIdFromUrlPath.equals(tenantId)) {
            throw new InvalidArgumentException(MessagesUtil.tenantIdtUrlError(tenantId));
        }
    }

    private static void validateCategoryIdMatchUrl(UUID categoryIdFromUrlPath, UUID categoryId) {
        if (!categoryIdFromUrlPath.equals(categoryId)) {
            throw new InvalidArgumentException(MessagesUtil.categoryIdUrlError(categoryId));
        }
    }

    private void validateUserCategoryBelongToTenant(UUID tenantId, UserCategory userCategory) {

        Optional<User> user = userRepository.getByTenantIdAndUserId(tenantId, userCategory.getUserId());
        if(user.isEmpty())
            throw  new InvalidArgumentException(MessagesUtil.userTenantError(userCategory.getUserId(), tenantId));

        Optional<Category> category = categoryRepository.getByTenantIdAndCategoryId(tenantId, userCategory.getCategoryId());
        if(category.isEmpty())
            throw  new InvalidArgumentException(MessagesUtil.categoryTenantError(userCategory.getCategoryId(), tenantId));


    }
}
