package com.example.demo.entity;

import java.util.List;

public class SalesOrder {
  private int id;
  private int customerId;
  private List<SalesOrderItem> items;

  public SalesOrder(){

  }
  
  // 這會導致insert失敗
  // public SalesOrder(int id, int customerId, List<SalesOrderItem> items) {
  // this.id = id;
  // this.customerId = customerId;
  // this.items = items;
  // }

  // 假如不需要SalesOrderItem
  public SalesOrder(int id, int customerId) {
    this.id = id;
    this.customerId = customerId;
  }

  public List<SalesOrderItem> getItems() {
    return this.items;
  }

  public void setItems(List<SalesOrderItem> items) {
    this.items = items;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

}
