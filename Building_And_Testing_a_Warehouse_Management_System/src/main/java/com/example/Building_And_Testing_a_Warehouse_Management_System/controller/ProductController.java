package com.example.Building_And_Testing_a_Warehouse_Management_System.controller;

import com.example.Building_And_Testing_a_Warehouse_Management_System.entity.ProductEntity;
import com.example.Building_And_Testing_a_Warehouse_Management_System.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {

        return new ResponseEntity<>(productService.saveProduct(productEntity), HttpStatus.CREATED);
    }


    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductEntity> updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        return ResponseEntity.ok(productService.updateStock(id, quantity));
    }
}
