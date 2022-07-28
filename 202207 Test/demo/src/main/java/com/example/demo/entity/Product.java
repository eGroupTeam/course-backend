package com.example.demo.entity;

public class Product {
  private int id;
  private String name;
  private String introduction;
  private int sort;
  private int price;
  private int organizationId;


  public Product(int id, String name, String introduction,int sort, int price ,int organizationId) {
    this.id = id;
    this.name = name;
    this.introduction = introduction;
    this.sort = sort;
    this.price = price;
    this.organizationId = organizationId;
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

  public String getIntroduction() {
    return this.introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public int getSort() {
    return this.sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getOrganizationId() {
    return this.organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

}
