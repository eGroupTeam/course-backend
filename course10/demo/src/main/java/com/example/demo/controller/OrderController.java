package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

  @GetMapping("/order/sales")
  public List<ProductSales> getProductSalesList() throws Exception {
    return dao.getProductSalesList();
  }

  @GetMapping("/order")
  public List<SalesOrder> getList() throws Exception {
    return dao.getList();
  }

  @GetMapping("/order/{id}")
  public SalesOrder getOrder(@PathVariable("id") int id) throws Exception {
    SalesOrder order = dao.getOrder(id);
    // if (orders.size() == 0) {
    // throw new ResponseStatusException(
    // HttpStatus.NOT_FOUND, id + ": 無此訂單資料");
    // }
    return order;
  }

  @GetMapping("/order/customer/{id}")
  public List<SalesOrder> getList(@PathVariable("id") int id) throws Exception {
    List<SalesOrder> orders = dao.getCutomerOrderList(id);
    if (orders.size() == 0) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "客戶:" + id + "，無訂單資料");
    }
    return orders;
  }

  @GetMapping("/order/status/{status}")
  public List<SalesOrder> getStatusList(@PathVariable("status") int status) throws Exception {
    List<SalesOrder> orders = dao.getStatusOrder(status);
    if (orders.size() == 0) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "狀態:"+ status + "，無訂單資料");
    }
    return orders;
  }


}
