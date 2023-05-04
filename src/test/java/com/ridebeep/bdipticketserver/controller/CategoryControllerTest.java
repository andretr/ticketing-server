package com.ridebeep.bdipticketserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ridebeep.bdipticketserver.exceptions.ResourceAlreadyExistsException;
import com.ridebeep.bdipticketserver.model.Category;
import com.ridebeep.bdipticketserver.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
@Import(CategoryTestBeans.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("check GET /tenants/{tenantId}/categories happy path")
    void testFindAllCategories() throws Exception {
        Mockito.when(categoryService.returnAllCategories(CategoryTestBeans.TENANT_ID)).thenReturn(CategoryTestBeans.getAllCategories());
        mvc.perform(get("/tenants/"+ CategoryTestBeans.TENANT_ID +"/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    @DisplayName("check GET /tenants/{tenantId}/categories/{categoryId} 201")
    void testFindCategoryById() throws Exception {
        Mockito
                .when(categoryService.getByTenantIdAndCategoryId(CategoryTestBeans.TENANT_ID, CategoryTestBeans.CATEGORY_ID))
                .thenReturn(Optional.of(CategoryTestBeans.getCategoryById()));
        mvc.perform(get("/tenants/"  + CategoryTestBeans.TENANT_ID +"/categories/" + CategoryTestBeans.CATEGORY_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("check GET /tenants/{tenantId}/categories/{categoryId} 404")
    void testFindCategoryById404() throws Exception {
        Mockito
                .when(categoryService.getByTenantIdAndCategoryId(CategoryTestBeans.TENANT_ID, CategoryTestBeans.CATEGORY_ID))
                .thenReturn(Optional.empty());
        mvc.perform(get("/tenants/"  + CategoryTestBeans.TENANT_ID +"/categories/" + CategoryTestBeans.CATEGORY_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("check POST /tenants/{tenantId}/categories 201 - body without categoryId")
    void addCategory201NoId() throws Exception {
        Category mockCategory = CategoryTestBeans.returnCategoryToAdd();
        Category savedCategory = CategoryTestBeans.getCategoryById();

        Mockito.when(categoryService.addCategory(CategoryTestBeans.TENANT_ID, mockCategory)).thenReturn(savedCategory);

        MvcResult result = mvc.perform(post("/tenants/" + CategoryTestBeans.TENANT_ID + "/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "tenantId": "6f811cc9-93aa-4663-bf32-945ad71d1bac",
                                    "categoryName": "TEST_CATEGORY"
                                }"""))
                .andExpect(status().isCreated())
                .andReturn();

        // verify object matches expected
        ObjectMapper mapper2 = new ObjectMapper().findAndRegisterModules();
        Category res = mapper2.readValue(result.getResponse().getContentAsString(), Category.class);
        assertEquals(res.getTenantId(), savedCategory.getTenantId());
        assertEquals(res.getCategoryName(), savedCategory.getCategoryName());
        assertEquals(res.getDefaultPriorityId(), savedCategory.getDefaultPriorityId());
        assertEquals(res.getCreated(), savedCategory.getCreated());
        assertEquals(res.getModified(), savedCategory.getModified());
    }

    @Test
    @DisplayName("check POST /tenants/{tenantId}/categories 201 - body with new categoryId")
    void addCategory201WithId() throws Exception {
        Category mockCategory = CategoryTestBeans.returnCategoryToAddWithId();
        Category savedCategory = CategoryTestBeans.getCategoryById();

        Mockito.when(categoryService.addCategory(CategoryTestBeans.TENANT_ID, mockCategory)).thenReturn(savedCategory);

        MvcResult result = mvc.perform(post("/tenants/" + CategoryTestBeans.TENANT_ID + "/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "categoryId": "ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae",
                                    "tenantId": "6f811cc9-93aa-4663-bf32-945ad71d1bac",
                                    "categoryName": "TEST_CATEGORY"
                                }"""))
                .andExpect(status().isCreated())
                .andReturn();

        // verify object matches expected
        ObjectMapper mapper2 = new ObjectMapper().findAndRegisterModules();
        Category res = mapper2.readValue(result.getResponse().getContentAsString(), Category.class);
        assertEquals(res, savedCategory);
    }

    @Test
    @DisplayName("check POST /tenants/{tenantId}/categories 400 - body with existing categoryId")
    void addCategory400WithId() throws Exception {
        Category savedCategory = CategoryTestBeans.getCategoryById();

        Mockito.when(categoryService.addCategory(CategoryTestBeans.TENANT_ID, savedCategory))
                .thenThrow(ResourceAlreadyExistsException.class);

        // Prepare body
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON=ow.writeValueAsString(savedCategory);

        mvc.perform(post("/tenants/" + CategoryTestBeans.TENANT_ID + "/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

   @Test
   void updateCategory() throws Exception  {
        Category categoryToUpdate = CategoryTestBeans.getCategoryById();
        Category updatedCategory = CategoryTestBeans.returnUpdatedCategory();

        Mockito.when(categoryService.updateCategory(CategoryTestBeans.TENANT_ID, CategoryTestBeans.CATEGORY_ID, categoryToUpdate)).thenReturn(updatedCategory);

        mvc.perform(put("/tenants/"+ CategoryTestBeans.TENANT_ID +"/categories/"+ CategoryTestBeans.CATEGORY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "categoryId": "ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae",
                            "tenantId": "6f811cc9-93aa-4663-bf32-945ad71d1bac",
                            "categoryName": "UPDATED_CATEGORY"
                        }"""))
                .andExpect(status().is2xxSuccessful());
    }

    /*
    @Test
    void testDeleteById() throws Exception {
        Category mockCategory = CategoryTestBeans.getCategoryById();
        Mockito.when(categoryService.findById(UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.of(mockCategory));

        mvc.perform(delete("/categories/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isNoContent());

        Mockito.when(categoryService.findById(UUID.fromString("add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.empty());
        mvc.perform(delete("/category/add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
                .andExpect(status().isNotFound())
                .andExpect(status().is4xxClientError());
    }
    */
}