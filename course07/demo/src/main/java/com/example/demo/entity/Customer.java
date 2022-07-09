package com.example.demo.entity;

import java.util.Objects;

public class Customer {
  private Long id;
  private String name;
  private String address;
  private int weight;

  public Customer() {
  }

  public Customer(Long id, String name, String address, int weight) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.weight = weight;
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

  public Customer id(Long id) {
    setId(id);
    return this;
  }

  public Customer name(String name) {
    setName(name);
    return this;
  }

  public Customer address(String address) {
    setAddress(address);
    return this;
  }

  public Customer weight(int weight) {
    setWeight(weight);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Customer)) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(id, customer.id) && Objects.equals(name, customer.name)
        && Objects.equals(address, customer.address) && weight == customer.weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, weight);
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        ", address='" + getAddress() + "'" +
        ", weight='" + getWeight() + "'" +
        "}";
  }

}