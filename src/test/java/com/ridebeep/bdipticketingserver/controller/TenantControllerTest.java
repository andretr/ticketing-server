package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.service.TenantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

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
    void testFindAllTenants() throws Exception {
        Mockito.when(tenantService.returnAllTenants()).thenReturn(TenantTestBeans.getAllTenants());
        mvc.perform(get("/tenants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
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
                            "tenantCode": "NORMAL_TENANT",
                            "name": "NORMAL_TENANT",
                            "hiddenTenant": false
                        }"""));
    }

   /* @Test
    void testDeleteById() throws Exception {
        Tenant mockTenant = TenantTestBeans.getTenantById();
        Mockito.when(tenantService.findById(UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.of(mockTenant));

        mvc.perform(delete("/tenants/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isNoContent());

        Mockito.when(tenantService.findById(UUID.fromString("add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.empty());
        mvc.perform(delete("/tenant/add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isNotFound())
                .andExpect(status().is4xxClientError());
    }*/

    /*@Test
    void addTenant() throws Exception {
        Tenant mockTenant = TenantTestBeans.returnTenantToAdd();
        Tenant savedTenant = TenantTestBeans.getTenantById();

        Mockito.when(tenantService.save(mockTenant)).thenReturn(savedTenant);

        mvc.perform(post("/tenants/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"localmotors2\"}"))
                .andExpect(status().isCreated());
    }

   @Test
   void updateTenant() throws Exception  {
        Tenant tenantToUpdate = TenantTestBeans.getTenantById();
        Tenant updatedTenant = TenantTestBeans.returnUpdatedTenant();

        Mockito.when(tenantService.findById(UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.of(updatedTenant));
        Mockito.when(tenantService.save(tenantToUpdate)).thenReturn(updatedTenant);

        mvc.perform(put("/tenants/update/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test name\"}"))
                .andExpect(status().is2xxSuccessful());

       Mockito.when(tenantService.findById(UUID.fromString("add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.empty());
       mvc.perform(delete("/tenant/add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
               .andExpect(status().isNotFound())
               .andExpect(status().is4xxClientError());
    }*/
}