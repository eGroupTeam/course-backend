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
  public List<Product> retrievePriProducts() throws SQLException, Exception{
      return dao.getList();
  }

  @GetMapping(value = {"/product/{id}"})
  public Product retrieveOneProduct(@PathVariable("id") Long id) throws SQLException, Exception{
    Product product = dao.get(id);
    if (product.getProductId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return product;
  }
  
   @PostMapping(value = "/product")
  public void processFormCreate(@RequestBody Product product) throws Exception {
      dao.insert(product);
    
  }

  @PutMapping(value = "/product/{id}")
  public void processFormUpdate(@RequestBody Product product, @PathVariable("id") Long id) throws Exception {
    if (product.getProductId() == null){
      product.setProductId(id);
    }
    
    int result = dao.update(product);
    if (result == 0){
      throw new SQLException("id: "+id+" 並不存在");
    }
  }

  @DeleteMapping("/product/{id}")
  public void deleteCustomerById(@PathVariable Long id) throws Exception {
    dao.delete(id);
  }

    
}
