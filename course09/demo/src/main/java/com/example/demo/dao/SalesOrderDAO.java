package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductSales;
import com.example.demo.entity.SalesOrder;
import com.example.demo.entity.ProductTime;

@Repository
public interface SalesOrderDAO {
  public int insert(SalesOrder order) throws Exception;
  public List<ProductTime> getSalesBetweenList(String time1, String time2) throws Exception;
  public List<ProductSales> getList() throws Exception;
}