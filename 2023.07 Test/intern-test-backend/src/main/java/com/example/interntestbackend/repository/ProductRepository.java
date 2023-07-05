package com.example.interntestbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interntestbackend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
}
