package com.ridebeep.bdipticketserver.controller;

import com.ridebeep.bdipticketserver.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


/** This controller should contain read-only functions. User creation/updates/deletion is handled by the bdip-organization-server*/
@RestController
public class UserController {

    /**
     * Return a list of all users by tenant id (with hidden tenants filtered out)
     * @return List<User>
     */
    @GetMapping("/tenants/{tenantId}/users")
    public ResponseEntity<List<User>> returnAllUsers(@PathVariable UUID tenantId) {
        return null;
    }

}
