package com.example.interntestbackend.service;

import java.util.List;

import com.example.interntestbackend.model.Product;

public interface ProductService {

    public void createProduct(Product product);

    public List<Product> getAllProduct();

    public void updateProduct(Product product);

    public void deleteProduct(int id);

    public Product getProductById(int id);

    public List<String> getAllProductsName();

}
