package com.example.demo.entity;

import java.util.List;

public class SalesOrder {
  private int id;
  private int customerId;
  private List<SalesOrderItem> items;

  public List<SalesOrderItem> getItems() {
    return this.items;
  }

  public void setItems(List<SalesOrderItem> items) {
    this.items = items;
  }

  public SalesOrder(int id, int customerId) {
    this.id = id;
    this.customerId = customerId;
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
