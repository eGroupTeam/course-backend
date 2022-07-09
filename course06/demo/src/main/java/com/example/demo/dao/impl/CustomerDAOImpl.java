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

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
  @Autowired
  private DataSource dataSource;
  //jdbc
  
  public Customer findOne(Long id){
    Customer customer = new Customer(-1l, "","",0);

    try {
      Connection conn = dataSource.getConnection();
      String sql = "select id, name, address, weight from customer where id = ?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()){
        customer=getCustomer(rs);
      }

      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return customer;
  }
  public List<Customer> findAll() {
    List<Customer> customers = new ArrayList<Customer>();
    try {
      Connection conn = dataSource.getConnection();
      String sql = "select id, name, address, weight from customer";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()){
        customers.add(getCustomer(rs));
      }
      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return customers;
  }

  public Customer getCustomer(ResultSet rs) throws SQLException{
    return new Customer(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getString("address"),
      rs.getInt("weight"));
  }
}
