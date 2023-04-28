package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Category;
import com.ridebeep.bdipticketingserver.service.CategoryService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
@Import(CategoryTestBeans.class)
class CategoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testFindAllCategories() throws Exception {
        Mockito.when(categoryService.returnAllCategories(CategoryTestBeans.TENANT_ID)).thenReturn(CategoryTestBeans.getAllCategories());
        mvc.perform(get("/tenants/"+ CategoryTestBeans.TENANT_ID +"/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((jsonPath("$", hasSize(1))));
    }

    @Test
    void testFindCategoryById() throws Exception {
        Mockito
                .when(categoryService.getByTenantIdAndCategoryId(CategoryTestBeans.TENANT_ID, CategoryTestBeans.CATEGORY_ID))
                .thenReturn(Optional.of(CategoryTestBeans.getCategoryById()));
        mvc.perform(get("/tenants/"  + CategoryTestBeans.TENANT_ID +"/categories/" + CategoryTestBeans.CATEGORY_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

//    @Test
//    void addCategory() throws Exception {
//        Category mockCategory = CategoryTestBeans.returnCategoryToAdd();
//        Category savedCategory = CategoryTestBeans.getCategoryById();
//
//        Mockito.when(categoryService.addCategory(CategoryTestBeans.TENANT_ID, mockCategory)).thenReturn(savedCategory);
//
//        mvc.perform(post("/tenants/"+ CategoryTestBeans.TENANT_ID +"/categories")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"categoryName\": \"TEST_CATEGORY\"}"))
//                .andExpect(status().isCreated());
//    }


    /*


   @Test
   void updateCategory() throws Exception  {
        Category categoryToUpdate = CategoryTestBeans.getCategoryById();
        Category updatedCategory = CategoryTestBeans.returnUpdatedCategory();

        Mockito.when(categoryService.findById(UUID.fromString("ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.of(updatedCategory));
        Mockito.when(categoryService.save(categoryToUpdate)).thenReturn(updatedCategory);

        mvc.perform(put("/categories/update/ffd97825-04b0-45c6-b3f0-ad8b7f6e0cae")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"test name\"}"))
                .andExpect(status().is2xxSuccessful());

       Mockito.when(categoryService.findById(UUID.fromString("add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))).thenReturn(Optional.empty());
       mvc.perform(delete("/category/add97825-04b0-45c6-b3f0-ad8b7f6e0cae"))
               .andExpect(status().isNotFound())
               .andExpect(status().is4xxClientError());
    }

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