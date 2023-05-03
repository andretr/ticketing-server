package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Category;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TestConfiguration
public class CategoryTestBeans {

    static final UUID TENANT_ID = UUID.fromString("6f811cc9-93aa-4663-bf32-945ad71d1bac");
    static final UUID CATEGORY_ID = UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae");
    static final String TEST_DATE = "2023-03-20T20:26:04.902+00:00";
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    static final LocalDateTime dateTime = LocalDateTime.parse(TEST_DATE, formatter);
    @Bean
    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(
                CATEGORY_ID,
                TENANT_ID,
                null,
                "TEST_CATEGORY",
                dateTime,
                dateTime));
        return categories;
    }
    @Bean
    public static Category getCategoryById() {
        return getAllCategories().get(0);
    }
    @Bean
    public static Category returnCategoryToAdd() {
        return new Category(
                null,
                TENANT_ID,
                null,
                "TEST_CATEGORY",
                null,
                null
        );
    }

    @Bean
    public static Category returnCategoryToAddWithId() {
        return new Category(
                CATEGORY_ID,
                TENANT_ID,
                null,
                "TEST_CATEGORY",
                null,
                null
        );
    }

    @Bean
    public static Category returnUpdatedCategory() {
        return new Category(
                CATEGORY_ID,
                TENANT_ID,
                null,
                "UPDATED_CATEGORY",
                null,
                null);
    }
}
