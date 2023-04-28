package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.service.TenantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TenantController.class)
@Import(TenantTestBeans.class)
class TenantControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private TenantService tenantService;

    @Test
    @DisplayName("check GET /tenants happy path")
    void testFindAllTenants() throws Exception {
        Mockito.when(tenantService.returnAllTenants()).thenReturn(TenantTestBeans.getAllTenants());
        mvc.perform(get("/tenants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    @DisplayName("Check GET /tenant/{tenantId} happy path")
    void testFindTenantById() throws Exception {
        Mockito
                .when(tenantService.returnTenantById(TenantTestBeans.getTenantById().getTenantId()))
                .thenReturn(Optional.of(TenantTestBeans.getTenantById()));
        mvc.perform(get("/tenants/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                        {
                            "tenantId": "ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae",
                            "tenantCode": "TEST_TENANT",
                            "name": "TEST_TENANT",
                            "hiddenTenant": false
                        }"""));
    }

    @Test
    @DisplayName("Check GET /tenant/{tenantId} 404")
    void testFindTenantById404() throws Exception {
        Mockito
                .when(tenantService.returnTenantById(TenantTestBeans.getTenantById().getTenantId()))
                .thenReturn(Optional.empty());
        mvc.perform(get("/tenants/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isNotFound());

    }
}