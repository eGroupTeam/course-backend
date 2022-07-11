package com.example.demo.entity;

public class DepartmentCount {
    private String department;
    private int count;

    public DepartmentCount() {
    }

    public DepartmentCount(String department, int count) {
        this.department = department;
        this.count = count;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DepartmentCount department(String department) {
        setDepartment(department);
        return this;
    }

    public DepartmentCount count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DepartmentCount)) {
            return false;
        }
        DepartmentCount departmentCount = (DepartmentCount) o;
        return Objects.equals(department, departmentCount.department) && count == departmentCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, count);
    }

    @Override
    public String toString() {
        return "{" +
            " department='" + getDepartment() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
    
}