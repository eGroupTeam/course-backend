package com.example.interntestbackend.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.interntestbackend.dto.ProductRequestDto;
import com.example.interntestbackend.dto.ProductResponseDto;
import com.example.interntestbackend.model.Organization;
import com.example.interntestbackend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.interntestbackend.model.Product;
import com.example.interntestbackend.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/products")
    public void createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Organization organization = organizationService.getOrganizationById(productRequestDto.getOrganizationId());
        Product product = new Product(productRequestDto.getName(), productRequestDto.getDescription(), productRequestDto.getSort(), productRequestDto.getPrice(), organization);
        service.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "产品不存在");
        }
        ProductResponseDto responseDTO = new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getSort(), product.getPrice(), product.getOrganization().getId());
        return responseDTO;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = service.getAllProduct();

        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for(Product product:products){
            responseDtos.add(new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getSort(), product.getPrice(), product.getOrganization().getId()));
        }
        return responseDtos;
    }

    @GetMapping("/products/name")
    public List<String> getAllProductsName() {
        List<String> productNames = service.getAllProductsName();
        if (productNames.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "無資料");
        }
        return productNames;
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody ProductRequestDto productRequestDto) {
        Product product = service.getProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "产品不存在");
        }

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setSort(productRequestDto.getSort());
        product.setPrice(productRequestDto.getPrice());

        Organization organization = organizationService.getOrganizationById(productRequestDto.getOrganizationId());
        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "组织不存在");
        }
        product.setOrganization(organization);

        service.updateProduct(product);
    }


    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
    }

}