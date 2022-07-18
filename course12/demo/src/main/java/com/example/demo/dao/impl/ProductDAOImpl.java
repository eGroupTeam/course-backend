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

import com.example.demo.dao.ProductDAO;
import com.example.demo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
  @Autowired
  private DataSource dataSource;

  private Product getProduct(ResultSet rs) throws SQLException, Exception {
    return new Product(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getInt("price"));
  }

  public Product get(int id) throws Exception {
    Product product = new Product(-1, "", 1);
    String sql = "select id, name, address, weight from product where id = ?";
    // String sql = "select id, name, address, weight from product where id = ?";
    // 當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery();) {
        if (rs.next()) {
          product = getProduct(rs);
        }
      } catch (Exception e) {
        throw e;
      }
    } catch (Exception e) {
      throw e;
    }
    return product;

  }

  public List<Product> getList() throws Exception {
    List<Product> products = new ArrayList<Product>();
    String sql = "select id, name, price from product";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();) {
      while (rs.next()) {
        products.add(getProduct(rs));
      }

    } catch (Exception e) {
      throw e;
    }
    return products;
  }

  public int insert(Product product) throws Exception {
    int result = 0;
    String sql = "insert into product (name, price) values(?, ?)";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, product.getName());
      stmt.setInt(2, product.getPrice());
      result = stmt.executeUpdate();
    } catch (Exception e) {
      // something wrong
      throw e;
    }
    return result;
  }

  public int delete(int id) throws Exception {
    int result = 0;
    String sql = "delete from product where id =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, id);
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }
}
