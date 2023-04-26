package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Priority;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tenants/{tenantId}/priorities")
public class PriorityController {

    @GetMapping
    public ResponseEntity<List<Priority>> returnAllPriorities(@PathVariable UUID tenantId) {
        return null;
    }

    @GetMapping("/{priorityId}")
    public ResponseEntity<Priority> getPriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<Priority> addPriority(@PathVariable UUID tenantId, @Valid @RequestBody Priority newPriority) {
        return null;
    }

    @PutMapping("/{priorityId}")
    public ResponseEntity<List<Priority>> updatePriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId, @Valid @RequestBody Priority newPriority) {
        return null;
    }

    @DeleteMapping("/{priorityId}")
    public ResponseEntity<List<Priority>> deletePriority(@PathVariable UUID tenantId, @PathVariable UUID priorityId) {
        return null;
    }



}
