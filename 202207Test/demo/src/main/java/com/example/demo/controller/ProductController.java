package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;

@RestController
@CrossOrigin
public class ProductController {
  @Autowired
  ProductDAO dao;

  @GetMapping(value = "/product")
  public List<Product> retrieveProducts() throws SQLException, Exception {
    return dao.getList();
  }

  @GetMapping(value = { "/product/{productId}" })
  public Product retrieveOneProduct(@PathVariable("productId") int productId) throws SQLException, Exception {
    Product product = dao.get(productId);
    if (product.getProductId() == -1) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "productId: " + productId + " 並不存在");
    }
    return product;
  }

  @PostMapping(value = "/product")
  public void processFormCreate(@RequestBody Product product) throws Exception {
    dao.insert(product);
  }

  @PutMapping(value = "/product/{productId}")
  public void processFormUpdate(@RequestBody Product product, @PathVariable("productId") int productId) throws Exception {
    if (product.getProductId() == 0){
      product.setProductId(productId);
    }
    
    int result = dao.update(product);
    if (result == 0){
      throw new SQLException("productId: "+productId+" 並不存在");
    }
  }

  @DeleteMapping(value = "/product/{productId}")
  public void delete(@PathVariable("productId") int productId) throws Exception {

    int result = dao.delete(productId);
    if (result == 0) {
      throw new SQLException("productId: " + productId + " 並不存在");
    }
  }

}
