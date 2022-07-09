package com.example.demo.entity;

import java.util.Objects;

public class Employee {
  private int id; 
  private String name;
  private String department;


  public Employee() {
  }

  public Employee(int id, String name, String department) {
    this.id = id;
    this.name = name;
    this.department = department;
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

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Employee id(int id) {
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

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(department, employee.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, department);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", department='" + getDepartment() + "'" +
      "}";
  }
}
