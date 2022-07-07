package com.example.demo.entity;

public class Customer {
  private String name;
  private Number age;

  public void setName(String name){
    this.name = name;
  }
  public void setName(Number age){
    this.age = age;
  }
  public String getName(){
    return name;
  }  
  public Number getage(){
    return age;
  }  
}
