package com.example.demo.entity;

import java.util.Objects;

public class Employee{
  private Long id;
  private String name;
  private String department;
  private int wage;


  public Employee() {
  }

  public Employee(Long id, String name, String department, int wage) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.wage = wage;
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

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public int getWage() {
    return this.wage;
  }

  public void setWage(int wage) {
    this.wage = wage;
  }

  public Employee id(Long id) {
    setId(id);
    return this;
  }

  public Employee name(String name) {
    setName(name);
    return this;
  }

  public Employee department(String department) {
    setDepartment(department);
    return this;
  }

  public Employee wage(int wage) {
    setWage(wage);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(department, employee.department) && wage == employee.wage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, department, wage);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", department='" + getDepartment() + "'" +
      ", wage='" + getWage() + "'" +
      "}";
  }
}