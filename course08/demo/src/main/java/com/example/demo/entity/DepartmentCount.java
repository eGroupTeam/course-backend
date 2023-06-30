package com.example.demo.entity;

public class DepartmentCount {
    private String department;
    private int count;

    public DepartmentCount(String department, int count){
        this.department = department;
        this.count = count;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }
}
