package com.example.demo.controller;

import java.sql.SQLException;
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
import com.example.demo.entity.TakeTimeRange;

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

  @GetMapping(value = {"/order/timerange/{time1}/{time2}"})
  public List<TakeTimeRange> retrieveTimeBetween(@PathVariable("time1") String time1,@PathVariable("time2") String time2) throws SQLException, Exception{
    List<TakeTimeRange> time = dao.getTimeRangeList(time1, time2);
    if (time.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return time;
  }

}
