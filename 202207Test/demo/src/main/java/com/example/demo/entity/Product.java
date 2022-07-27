package com.example.demo.entity;

public class Product {
  private int id;
  private String name;
  private String desc;
  private int order;
  private int price;
  private int dep;

  public Product(int id, String name, String desc, int order, int price, int dep) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.order = order;
    this.price = price;
    this.dep = dep;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getOrder() {
    return this.order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getDep() {
    return this.dep;
  }

  public void setDep(int dep) {
    this.dep = dep;
  }
}