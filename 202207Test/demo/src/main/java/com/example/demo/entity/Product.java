package com.example.demo.entity;

public class Product {
  private int productId;
  private String productName;
  private String productDesc;
  private int productSort;
  private int productPrice;
  private int organizationId;

  public Product() {
    super();
  }

  public Product(int productId, String productName, String productDesc, int productSort, int productPrice, int organizationId) {
    super();
    this.productId = productId;
    this.productName = productName;
    this.productDesc = productDesc;
    this.productSort = productSort;
    this.productPrice = productPrice;
    this.organizationId = organizationId;
  }


  public int getProductId() {
    return this.productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDesc() {
    return this.productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public int getProductSort() {
    return this.productSort;
  }

  public void setProductSort(int productSort) {
    this.productSort = productSort;
  }

  public int getProductPrice() {
    return this.productPrice;
  }

  public void setProductPrice(int productPrice) {
    this.productPrice = productPrice;
  }

  public int getOrganizationId() {
    return this.organizationId;
  }

  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
  }

public int getId() {
    return 0;
}
  

}
