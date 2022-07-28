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
        rs.getString("introduction"),
        rs.getInt("sort"),
        rs.getInt("price"),
        rs.getInt("organizationId"));
  }

  public Product get(int id) throws Exception {
    Product product = new Product(-1, "", "", 1, 0, 1);
    String sql = "select * from organization_product where id = ?";
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
    String sql = "select id, name, introduction, sort, price, organizationId from organization_product";
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
    String sql = "insert into organization_product (id, name, introduction, sort, price, organizationId) values(?, ? ,?, ?, ?, ?)";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, product.getId());
      stmt.setString(2, product.getName());
      stmt.setString(3, product.getIntroduction());
      stmt.setInt(4, product.getSort());
      stmt.setInt(5, product.getPrice());
      stmt.setInt(6, product.getOrganizationId());
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }
  public int update(Product product) throws Exception {
    int result = 0;
    String sql = "update organization_product set name=?, introduction=?, sort=?, price=?, organizationId=? where id =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, product.getName());
      stmt.setString(2, product.getIntroduction());
      stmt.setInt(3, product.getSort());
      stmt.setInt(4, product.getPrice());
      stmt.setInt(5, product.getOrganizationId());
      stmt.setInt(6, product.getId());
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }

  public int delete(int id) throws Exception {
    int result = 0;
    String sql = "delete from organization_product where id =?";
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
