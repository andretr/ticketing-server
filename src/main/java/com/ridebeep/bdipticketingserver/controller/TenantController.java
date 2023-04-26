package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Tenant;
import com.ridebeep.bdipticketingserver.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


/** This controller should contain read-only functions. Tenant creation/updates/deletion is handled by the bdip-organization-server*/
@RestController
@RequestMapping(value = "tenants")
public class TenantController {

    @Autowired
    TenantService tenantService;

    /**
     * Return a list of all tenants (with hidden tenants filtered out)
     * @return List<Tenant>
     */
    @GetMapping
    public ResponseEntity<List<Tenant>> returnAllTenants() {
        return ResponseEntity
                .ok(tenantService.returnAllTenants());
    }

    /**
     * Return a specific (non-hidden) tenant by unique tenant code
     * @return Tenant | Resource not found exception
     */
    @GetMapping("code/{tenantCode}")
    public ResponseEntity<Tenant> returnAllTenantsByCode(@PathVariable("tenantCode") String tenantCode) {

        Optional<Tenant> tenant = tenantService.returnAllTenantsByCode(tenantCode);
        return tenant
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
