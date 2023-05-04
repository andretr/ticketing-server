package com.ridebeep.bdipticketserver.controller;

import com.ridebeep.bdipticketserver.model.Priority;
import com.ridebeep.bdipticketserver.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tenants/{tenantId}/priorities")
public class PriorityController {

    @Autowired
    PriorityService priorityService;

    @GetMapping
    public ResponseEntity<List<Priority>> returnAllPriorities(@PathVariable UUID tenantId) {
        List<Priority> priorities = priorityService.returnAllPriorities(tenantId);
        if(priorities.isEmpty())
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(priorities);
    }

    @GetMapping("/{priorityId}")
    public ResponseEntity<Priority> getPriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId) {
        Optional<Priority> priority = priorityService.getByTenantIdAndPriorityId(tenantId, priorityId);
        return priority
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping()
    public ResponseEntity<Priority> addPriority(@PathVariable UUID tenantId, @Valid @RequestBody Priority newPriority) {
        Priority priority = priorityService.addPriority(tenantId, newPriority);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(priority);
    }

    @PutMapping("/{priorityId}")
    public ResponseEntity<Priority>  updatePriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId, @Valid @RequestBody Priority newPriority) {
        Priority priority = priorityService.updatePriority(tenantId, priorityId, newPriority);
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/{priorityId}")
    public ResponseEntity<Object> deletePriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId) {
        priorityService.deletePriority(tenantId, priorityId);
        return ResponseEntity.noContent().build();
    }



}
