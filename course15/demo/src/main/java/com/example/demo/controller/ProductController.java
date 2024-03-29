package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication.isAuthenticated()) {
      System.out.println("authenticated");
    }
    System.out.println(authentication.getName());
    System.out.println(authentication.getPrincipal());
    Object principal = authentication.getPrincipal();
    String username = "";
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    System.out.println("username:" + username);

    return dao.getList();
  }

  @GetMapping(value = { "/product/{id}" })
  public Product retrieveOneProduct(@PathVariable("id") int id) throws SQLException, Exception {
    Product product = dao.get(id);
    if (product.getId() == -1) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "id: " + id + " 並不存在");
    }
    return product;
  }

  @PostMapping(value = "/product")
  public void processFormCreate(@RequestBody Product product) throws Exception {
    dao.insert(product);
  }

  @PutMapping(value = "/product/{id}")
  public void processFormUpdate(@RequestBody Product product, @PathVariable("id") int id) throws Exception {
    if (product.getId() == 0) {// if not set, getId() will return 0
      product.setId(id);
    }

    int result = dao.update(product);
    if (result == 0) {
      throw new SQLException("id: " + id + " 並不存在");
    }
  }

  @DeleteMapping(value = "/product/{id}")
  public void delete(@PathVariable("id") int id) throws Exception {

    int result = dao.delete(id);
    if (result == 0) {
      throw new SQLException("id: " + id + " 並不存在");
    }
  }

}
