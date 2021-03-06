package com.example.demo.entity;
public class Customer {
  private Long id;
  private String name;
  private String address;
  private int weight;

  public Customer(Long id, String name, String address, int weight) throws Exception {
    this.id = id;
    this.name = name;
    this.address = address;
    if (weight > 0){
      this.weight = weight;
    }
    else {
      throw new Exception("體重必須大於0");
    }

  }


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
   
  
}

