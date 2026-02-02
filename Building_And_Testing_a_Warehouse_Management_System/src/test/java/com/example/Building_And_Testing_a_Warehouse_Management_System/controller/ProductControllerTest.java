package com.example.Building_And_Testing_a_Warehouse_Management_System.controller;

import com.example.Building_And_Testing_a_Warehouse_Management_System.entity.ProductEntity;
import com.example.Building_And_Testing_a_Warehouse_Management_System.exception.ResourceNotFoundException;
import com.example.Building_And_Testing_a_Warehouse_Management_System.service.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class) //Loads only the web layer (controllers).
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //fakeHttpClient
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    //Post Test
    @Test
    void createProduct_shouldReturn201() throws Exception {

        ProductEntity product = new ProductEntity(1L, "Mouse", 10, 20.0);


        when(productService.saveProduct(any(ProductEntity.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mouse"));


    }

    // Get Test
    @Test
    void getProduct_whenExists_shouldReturn200() throws Exception {
        ProductEntity product = new ProductEntity(1L, "Keyboard", 5, 30.0);

        when(productService.getProductById(1L))
                .thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"));
    }

    // Scenario C (404)
    @Test
    void getProduct_whenNotExists_shouldReturn404() throws Exception {
        when(productService.getProductById(99L))
                .thenThrow(new ResourceNotFoundException("Not Found"));

        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());
    }
}