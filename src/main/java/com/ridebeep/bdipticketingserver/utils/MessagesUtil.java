package com.ridebeep.bdipticketingserver.utils;

import java.text.MessageFormat;
import java.util.UUID;

public class MessagesUtil {

    private MessagesUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String tenantIdtUrlError(UUID tenantId) {
        return MessageFormat.format("tenant id {0} must match request url", tenantId);
    }

    public static String categoryIdUrlError(UUID categoryId) {
        return MessageFormat.format("category id {0} must match request url", categoryId);
    }

    public static String priorityIdUrlError(UUID priorityId) {
        return MessageFormat.format("priority id {0} must match request url", priorityId);
    }

    public static String userTenantError(UUID userId, UUID tenantId) {
        return MessageFormat.format("Could not find user with id {0} and tenant id {1}", userId, tenantId);
    }

    public static String categoryTenantError(UUID categoryId, UUID tenantId) {
        return MessageFormat.format("Could not find category with id {0} and tenant id {1}", categoryId, tenantId);
    }

    public static String userCategoryError(UUID categoryId, UUID userId) {
        return MessageFormat.format("Could not find category with id {0} and user id {1}", categoryId, userId);
    }

    public static String priorityTenantError(UUID priorityId, UUID tenantId) {
        return MessageFormat.format("Could not find priority with id {0} and tenant id {1}", priorityId, tenantId);
    }

    public static String categoryIdAlreadyExistsError(UUID categoryId) {
        return MessageFormat.format("Category with id {0} already exists", categoryId);
    }

    public static String priorityIdAlreadyExistsError(UUID priorityId) {
        return MessageFormat.format("Priority with id {0} already exists", priorityId);
    }




}
