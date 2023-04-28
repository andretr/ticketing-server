package com.ridebeep.bdipticketingserver.utils;

import java.text.MessageFormat;
import java.util.UUID;

public class MessagesUtil {

    private MessagesUtil() {
        throw new IllegalStateException("Utility class");
    }
    public static String tenantCategoryError(UUID categoryId, UUID tenantId) {
        return MessageFormat.format("Could not find category with id {0} and tenant id {1}", categoryId, tenantId);
    }

    public static String userCategoryError(UUID categoryId, UUID userId) {
        return MessageFormat.format("Could not find category with id {0} and user id {1}", categoryId, userId);
    }
}
