package com.example.interntestbackend.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.interntestbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.interntestbackend.model.Product;
import com.example.interntestbackend.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<String> getAllProductsName() {
        List<Product> productList = productRepository.findAll();
        List<String> nameList = new ArrayList<>();
        for (Product product : productList) {
            nameList.add(product.getName());
        }
        return nameList;
    }

}
