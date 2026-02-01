package com.example.Building_And_Testing_a_Warehouse_Management_System.service;

import com.example.Building_And_Testing_a_Warehouse_Management_System.entity.ProductEntity;
import com.example.Building_And_Testing_a_Warehouse_Management_System.exception.ResourceNotFoundException;
import com.example.Building_And_Testing_a_Warehouse_Management_System.repository.ProductRepo;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    @Test
    void saveProduct_shouldReturnSavedProducts(){

        ProductEntity product = new ProductEntity(null, "Mouse", 10, 20.0);
        ProductEntity saveProduct = new ProductEntity(1L, "Mouse", 10, 20.0);

        when(productRepo.save(product)).thenReturn(saveProduct);

        ProductEntity result = productService.saveProduct(product);
        assertNotNull(result.getId());
    }


    @Test
    void getProductById_whenExists_shouldReturnProduct() {
        ProductEntity product = new ProductEntity(1L, "Keyboard", 5, 30.0);

        when(productRepo.findById(1L))
                .thenReturn(Optional.of(product));

        ProductEntity result = productService.getProductById(1L);

        assertEquals("Keyboard", result.getName());
    }


    @Test
    void getProductById_whenNotExists_shouldThrowException() {
        when(productRepo.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> productService.getProductById(99L));
    }


}