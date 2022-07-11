package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SalesOrderDAO;
import com.example.demo.entity.ProductSales;
import com.example.demo.entity.SalesOrder;

@RestController
public class OrderController {
  @Autowired
  SalesOrderDAO dao;

  @PostMapping("/order")
  public void insert(@RequestBody SalesOrder order) throws Exception {
    System.out.println(dao.insert(order));
  }

  @GetMapping("/order")
  public List<ProductSales> getList() throws Exception {
    return dao.getList();
  }

}
