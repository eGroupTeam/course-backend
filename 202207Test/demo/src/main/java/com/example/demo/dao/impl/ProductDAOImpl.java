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
                rs.getInt("product_id"),
                rs.getString("name"),
                rs.getString("expla"),
                rs.getInt("seq"),
                rs.getInt("price"),
                rs.getInt("org_id"));
    }

    public Product get(int id) throws Exception {
        Product product = new Product(-1, "", "", 1, 0, 0);
        String sql = "select * from organization_product where product_id = ?";
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
        String sql = "select * from organization_product";
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
        String sql = "insert into organization_product (name, expla, seq, price, org_id) values(?, ?, ?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getExpla());
            stmt.setInt(3, product.getSeq());
            stmt.setInt(4, product.getPrice());
            stmt.setInt(5, product.getOrg_id());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public int update(Product product) throws Exception {
        int result = 0;
        String sql = "update organization_product set name=?, expla=?, seq=?, price=?, org_id=? where product_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getExpla());
            stmt.setInt(3, product.getSeq());
            stmt.setInt(4, product.getPrice());
            stmt.setInt(5, product.getOrg_id());
            stmt.setInt(6, product.getId());
            result = stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public int delete(int id) throws Exception {
        int result = 0;
        String sql = "delete from organization_product where product_id=?";
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
