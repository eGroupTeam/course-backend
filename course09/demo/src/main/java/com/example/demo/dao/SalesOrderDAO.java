package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductSales;
import com.example.demo.entity.SalesOrder;
import com.example.demo.entity.TakeTimeRange;

@Repository
public interface SalesOrderDAO {
  public int insert(SalesOrder order) throws Exception;

  public List<ProductSales> getList() throws Exception;

  public List<TakeTimeRange> getTimeRangeList(String time1, String time2) throws Exception;
}