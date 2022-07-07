package com.example.demo.entity;
public class Employee{
    private Long id;
    private String name;
    private String gender;
    private String department;

    public Employee(Long id, String name, String gender, String department ) {
      this.name = name;
      this.department = department;
      this.gender = gender;
    }

    public Employee(String string, String string2, String string3) {
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDepartment() {
      return department;
    }

    public void setDepartment(String department) {
      this.department = department;
    }

    public String getGender() {
      return gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }
}