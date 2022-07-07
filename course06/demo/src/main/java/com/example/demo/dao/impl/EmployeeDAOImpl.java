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
  private DataSource DataSource;
  //jdbc
  
  public List<Employee> findall() {
    List<Employee> employee = new ArrayList<Employee>();
    try {
      Connection Conn = DataSource.getConnection();
      String sql1 = "select * from employee";
      PreparedStatement stmts = Conn.prepareStatement(sql1);
      ResultSet rs1 = stmts.executeQuery();
      while (rs1.next()){
        employee.add(getEmployee(rs1));
      }
      Conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return employee;
  }

  public Employee getEmployee(ResultSet rs1) throws SQLException{
    return new Employee(
      rs1.getLong("id"),
      rs1.getString("name"),
      rs1.getString("address"),
      rs1.getInt("weight"));
  }
}
