package com.example.demo.entity;
public class Employee{
    private String name;
    private String department;
    private Long id;
    private String gender;

    public Employee(Long id ,String name,  String gender,String department ) {
      this.name = name;
      this.department = department;
      this.gender = gender;
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

    public void setDepartment(String department) {
      this.department = department;
    }
}