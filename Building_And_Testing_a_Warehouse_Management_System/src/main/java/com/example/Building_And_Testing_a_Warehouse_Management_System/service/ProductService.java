package com.example.Building_And_Testing_a_Warehouse_Management_System.service;

import com.example.Building_And_Testing_a_Warehouse_Management_System.entity.ProductEntity;
import com.example.Building_And_Testing_a_Warehouse_Management_System.exception.ResourceNotFoundException;
import com.example.Building_And_Testing_a_Warehouse_Management_System.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public ProductEntity getProductById(Long id) {

        return productRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Product not found with id: " + id
                ));
    }


    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepo.save(productEntity);
    }


    public ProductEntity updateStock(Long id, Integer quantity) {

        ProductEntity existingProduct = getProductById(id);
        existingProduct.setQuantity(quantity);
        return productRepo.save(existingProduct);
    }
}
