package com.example.demo.entity;

public class SalesOrderItem {
  private int id;
  private int orderId;
  private int productId;
  private int amount;
  private int price;

  public SalesOrderItem(){

  }
  
  public SalesOrderItem(int id, int orderId, int productId, int amount, int price) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.amount = amount;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getProductId() {
    return this.productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}
