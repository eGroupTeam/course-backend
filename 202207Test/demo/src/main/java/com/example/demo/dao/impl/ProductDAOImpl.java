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
        rs.getInt("productId"),
        rs.getString("productName"),
        rs.getString("productDesc"),
        rs.getInt("productSort"),
        rs.getInt("productPrice"),
        rs.getInt("organizationId")
        );
  }

  public Product get(int productId) throws Exception {
    Product product = new Product(-1, "", null, 1, productId, productId);
    String sql = "select productId, name, address, weight from product where productId = ?";
    // String sql = "select productId, name, address, weight from product where productId = ?";
    // 當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, productId);
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
    String sql = "select productId, productName, productDesc, productSort, productPrice, organizationId from organization_product";
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
    String sql = "insert into organization_product (productName, productDesc, productSort, productPrice, organizationId) values(?, ?, ?, ?, ?)";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, product.getProductName());
      stmt.setString(2, product.getProductDesc());
      stmt.setInt(3, product.getProductSort());
      stmt.setInt(4, product.getProductPrice());
      stmt.setInt(5, product.getOrganizationId());
      result = stmt.executeUpdate();
    } catch (Exception e) {
      // something wrong
      throw e;
    }
    return result;
  }

  public int delete(int productId) throws Exception {
    int result = 0;
    String sql = "delete from organization_product where productId =?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1, productId);
      result = stmt.executeUpdate();
    } catch (Exception e) {
      throw e;
    }
    return result;
  }

  public int update(Product product) throws Exception{
    int result = 0;
    String sql = "update organization_product set productName=?, productDesc=?, productSort=?, productPrice=?, organizationId=? where productId =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
        stmt.setString(1, product.getProductName());
        stmt.setString(2, product.getProductDesc());
        stmt.setInt(3, product.getProductSort());
        stmt.setInt(4, product.getProductPrice());
        stmt.setInt(5, product.getOrganizationId());
        stmt.setInt(6, product.getProductId());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    return result;
  }
}
