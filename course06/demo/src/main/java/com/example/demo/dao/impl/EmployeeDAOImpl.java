package com.example.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    public DataSource dataSource;

    public Employee findOne(Long id){
        Employee employee = new Employee(-1l, "","");
        try {
          Connection conn = dataSource.getConnection();
          String sql = "select id, name, department from employee where id = ?";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setLong(1, id);
          //System.out.println(stmt);
          ResultSet rs = stmt.executeQuery();
          if (rs.next()){
            employee=getEmployee(rs);
          }
    
          conn.close();
        } catch(Exception e) {
          //something wrong
          System.out.println(e);
        }
        return employee;
    }
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select id, name, department from employee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(getEmployee(rs));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employees;
    }

    public Employee getEmployee(ResultSet rs) throws SQLException{
        return new Employee(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("department")
        );
    }
}
