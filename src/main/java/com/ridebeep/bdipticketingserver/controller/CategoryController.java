package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tenants/{tenantId}/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<Category>> returnAllCategories(@PathVariable UUID tenantId) {
        return null;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<Category> addCategory(@PathVariable UUID tenantId, @Valid @RequestBody Category newCategory) {
        return null;
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<List<Category>> updateCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId, @Valid @RequestBody Category newCategory) {
        return null;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable UUID tenantId, @PathVariable UUID categoryId) {
        return null;
    }



}
