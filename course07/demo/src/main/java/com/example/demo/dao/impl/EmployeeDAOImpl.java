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
  //jdbc
  
  public Employee findOne(Long id) throws Exception{
    Employee employee = new Employee(-1l,"","");
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select * from employee where id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
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
    List<Employee> employee = new ArrayList<Employee>();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select * from employee";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()){
        employee.add(getEmployee(rs));
      }
      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return employee;
  }

  public Employee getEmployee(ResultSet rs) throws SQLException, Exception{
    return new Employee(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getString("department")
      );
  }
  public int createEmployee(Employee employee){
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "insert into employee (name, department) values(?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, employee.getName());
      stmt.setString(2, employee.getDepartment());
      result = stmt.executeUpdate();
      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return result;
  }
  public int updateEmployee(Employee employee){
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
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return result;
  }

  public int deleteEmployee(Long id){
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "delete from employee where id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      result = stmt.executeUpdate();
      conn.close();
    } catch(Exception e) {
      System.out.println(e);
    }
    return result;
  }
  
}
