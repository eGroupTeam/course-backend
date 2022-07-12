package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductSales;
import com.example.demo.entity.SalesOrder;

@Repository
public interface SalesOrderDAO {
  public int insert(SalesOrder order) throws Exception;

  public List<ProductSales> getProductSalesList() throws Exception;

  public List<SalesOrder> getList() throws Exception;

  public List<SalesOrder> getCutomerOrderList(int customerId) throws Exception;

  public SalesOrder getOrder(int orderId) throws Exception;

  public List<SalesOrder> getOrderListStatus(int status) throws Exception;
}