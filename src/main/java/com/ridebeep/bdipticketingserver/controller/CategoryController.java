package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            Category category = categoryService.addCategory(tenantId, newCategory);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId, @Valid @RequestBody Category newCategory) {
        try {
            Category category = categoryService.updateCategory(tenantId, categoryId, newCategory);
            return ResponseEntity.ok(category);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {
        try {
            categoryService.deleteCategory(tenantId, categoryId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



}
