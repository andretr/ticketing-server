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
                "NORMAL_TENANT",
                "NORMAL_TENANT",
                false));
        /*tenants.add(new Tenant(
                UUID.fromString("493e1871-c03c-4633-a0ff-6d86d274f47a"),
                "HIDDEN_TENANT",
                "HIDDEN_TENANT",
                true));*/
        return tenants;
    }
    @Bean
    public static Tenant getTenantById() {
        Tenant mockTenant = new Tenant(
                UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"),
                "NORMAL_TENANT",
                "NORMAL_TENANT",
                false);
        return mockTenant;
    }
   /* @Bean
    public static Tenant returnTenantToAdd() {
         Tenant mockTenant =new Tenant();
        mockTenant.setName("NORMAL_TENANT");
        return mockTenant;
    }

    @Bean
    public static Tenant returnUpdatedTenant() {
        return new Tenant(
                UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"),
                "NORMAL_TENANT",
                "NORMAL_TENANT_UPDATED",
                false);
    }*/
}
