package com.example.Building_And_Testing_a_Warehouse_Management_System.repository;

import com.example.Building_And_Testing_a_Warehouse_Management_System.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
