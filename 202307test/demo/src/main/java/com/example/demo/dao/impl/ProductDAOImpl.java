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

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{
     @Autowired
      private DataSource dataSource;
  
  private Product getProduct(ResultSet rs) throws SQLException, Exception{
    return new Product(
      rs.getLong("productId"),
      rs.getString("productName"),
      rs.getInt("productPrice"));
  }

 public Product get(Long id) throws Exception{
    Product product = new Product(-1l,"",1);
    String sql = "select productId, productName, productPrice from product where productId = ?";
    //String sql = "select id, name, address, weight from product where id = ?";
    //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ){
      stmt.setLong(1, id);
      try (ResultSet rs = stmt.executeQuery();){
        if (rs.next()){
          product=getProduct(rs);
        } 
      }
      catch (Exception e){
        throw e;
      }
    }
    catch (Exception e){
      throw e;
    }
    return product;

  }

  public List<Product> getList() throws Exception{
    List<Product> products = new ArrayList<Product>();
    String sql = "select productId, productName, productPrice from product";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        products.add(getProduct(rs));
      }  
      
    } catch(Exception e) {
      throw e;
    }
    return products;
  }

  public int insert(Product product) throws Exception{
    int result = 0;
    String sql = "insert into product (productName, productPrice) values(?, ?)";
    try( 
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {    
      stmt.setString(1, product.getProductName());
      stmt.setInt(2, product.getProductPrice());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      //something wrong
      throw e;
    }
    return result;
  }
  public int update(Product product) throws Exception{
    int result = 0;
    String sql = "update product set productName=?, productPrice=? where productId =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, product.getProductName());
      stmt.setInt(2, product.getProductPrice());
      stmt.setLong(3, product.getProductId());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    return result;
    }

    public void delete(Long id) throws Exception{
   
    String sql = "delete from product where productId =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    
    }
}
