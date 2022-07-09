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
  
  public Customer findOne(Long id) throws Exception{
    Customer customer = new Customer(-1l,"","",1);
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

  public Customer getCustomer(ResultSet rs) throws SQLException, Exception{
    return new Customer(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getString("address"),
      rs.getInt("weight"));
  }
  public int createCustomer(Customer customer){
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "insert into customer (name, address, weight) values(?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, customer.getName());
      stmt.setString(2, customer.getAddress());
      stmt.setInt(3, customer.getWeight());
      result = stmt.executeUpdate();
      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }
    return result;
  }
  public int updateCustomer(Customer customer){
    int result = 0;
    try {
      Connection conn = dataSource.getConnection();
      String sql = "update customer set name=?, address=?, weight=? where id =?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, customer.getName());
      stmt.setString(2, customer.getAddress());
      stmt.setInt(3, customer.getWeight());
      stmt.setLong(4, customer.getId());
      result = stmt.executeUpdate();
      conn.close();
    } catch(Exception e) {
      //something wrong
      System.out.println(e);
    }


    return result;
  }

  public int deleteCustomer(Long id){
    int result = 0;
        try {
            Connection conn = dataSource.getConnection();
            String sql = "DELETE FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            // something wrong
            System.out.println(e);
        }
        return result;
  }
  
}
