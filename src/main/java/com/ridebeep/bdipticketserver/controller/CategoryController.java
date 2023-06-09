package com.ridebeep.bdipticketserver.controller;

import com.ridebeep.bdipticketserver.model.Category;
import com.ridebeep.bdipticketserver.model.User;
import com.ridebeep.bdipticketserver.model.UserCategory;
import com.ridebeep.bdipticketserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tenants/{tenantId}/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> returnAllCategories(@PathVariable UUID tenantId) {
        List<Category> categories = categoryService.returnAllCategories(tenantId);
        if(categories.isEmpty())
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {

        Optional<Category> category = categoryService.getByTenantIdAndCategoryId(tenantId, categoryId);
        return category
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@PathVariable UUID tenantId, @Valid @RequestBody Category newCategory) {
        Category category = categoryService.addCategory(tenantId, newCategory);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(category);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId, @Valid @RequestBody Category newCategory) {
        Category category = categoryService.updateCategory(tenantId, categoryId, newCategory);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {
        categoryService.deleteCategory(tenantId, categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{categoryId}/users")
    public ResponseEntity<List<User>> getAllUsersByCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {
        List<User> usersByCategory = categoryService.getAllUsersByCategory(tenantId, categoryId);
        return ResponseEntity.ok(usersByCategory);

    }
    @PostMapping("/{categoryId}/users")
    public ResponseEntity<UserCategory> addUserToCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId, @Valid @RequestBody UserCategory newUserCategory) {
        UserCategory userCategory  = categoryService.addUserCategory(tenantId, categoryId, newUserCategory);
        return ResponseEntity.ok(userCategory);

    }

    @DeleteMapping("/{categoryId}/users/{userId}")
    public ResponseEntity<Object> deleteUserFromCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId, @PathVariable UUID userId) {
        categoryService.deleteUserFromCategory(tenantId, categoryId, userId);
        return ResponseEntity.noContent().build();
    }
}