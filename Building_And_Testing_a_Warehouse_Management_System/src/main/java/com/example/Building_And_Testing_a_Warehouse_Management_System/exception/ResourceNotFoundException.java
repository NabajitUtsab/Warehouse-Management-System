package com.example.Building_And_Testing_a_Warehouse_Management_System.exception;


import java.util.NoSuchElementException;

public class ResourceNotFoundException extends NoSuchElementException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
