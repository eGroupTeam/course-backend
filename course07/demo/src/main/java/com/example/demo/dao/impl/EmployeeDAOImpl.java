package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    private DataSource dataSource;
    // jdbc

    public Employee findOne(int id) throws Exception {
        Employee employee = new Employee(-1, "", "");
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select * from employee where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = getEmployee(rs);
            }

            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return employee;
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            Connection conn = dataSource.getConnection();
            String sql = "select * from employee";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(getEmployee(rs));
            }
            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return employees;
    }

    public Employee getEmployee(ResultSet rs) throws SQLException, Exception {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("department"));
    }

    public int createEmployee(Employee employee) {
        int result = 0;
        try {
            Connection conn = dataSource.getConnection();
            String sql = "insert into employee (name, department) values(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDepartment());
            // stmt.setLong(3, employee.getId());
            result = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return result;
    }

    public int updateEmployee(Employee employee) {
        int result = 0;
        try {
            Connection conn = dataSource.getConnection();
            String sql = "update employee set name=?, department=? where id =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDepartment());
            stmt.setLong(3, employee.getId());
            result = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return result;
    }

    public int deleteEmployee(Employee employee) {
        int result = 0;
        try {
            Connection conn = dataSource.getConnection();
            String sql = "DELETE FROM employee WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, employee.getId());
            result = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return result;
    }
}