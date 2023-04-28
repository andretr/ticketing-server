package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Tenant;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@TestConfiguration
public class TenantTestBeans {
    @Bean
    public static List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        tenants.add(new Tenant(
                UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"),
                "TEST_TENANT",
                "TEST_TENANT",
                false));
        return tenants;
    }
    @Bean
    public static Tenant getTenantById() {
        return getAllTenants().get(0);
    }

}
