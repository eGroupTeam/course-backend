package com.example.demo.entity;
public class Employee{
    private Long id;
    private String name;
    private String department;
    private int wage;

    public Employee(){

    }

    public Employee(Long id, String name, String department, int wage) {
      this.id = id;
      this.name = name;
      this.department = department;
      this.wage = wage;
    }

    public Long getId(){
      return id;
    }

    public void setId(Long id){
      this.id = id;
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
    
    public int getWage(){
      return wage;
    }

    public void setWage(int wage){
      this.wage = wage;
    }
}