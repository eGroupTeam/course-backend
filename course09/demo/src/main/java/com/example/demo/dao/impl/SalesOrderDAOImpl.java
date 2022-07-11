package com.example.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.SalesOrderDAO;
import com.example.demo.entity.ProductSales;
import com.example.demo.entity.SalesOrder;
import com.example.demo.entity.SalesOrderItem;
import com.example.demo.entity.TakeTimeRange;

@Repository
public class SalesOrderDAOImpl implements SalesOrderDAO {
  @Autowired
  private DataSource dataSource;

  public List<ProductSales> getList() throws Exception {
    List<ProductSales> customers = new ArrayList<ProductSales>();
    String sql = "select product_id, name, SUM(amount) as total from sales_order_item join product on product.id = sales_order_item.product_id group by product_id";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
        customers.add(
            new ProductSales(
                result.getInt("product_id"),
                result.getString("name"),
                result.getInt("total")));
      }

    }
    return customers;
  }

  public List<TakeTimeRange> getTimeRangeList(String time1, String time2) throws Exception {
    List<TakeTimeRange> time = new ArrayList<TakeTimeRange>();
    String sql = "select product_id, name, SUM(amount), order_time as TimeRangeOrder from (sales_order_item join product on product.id = sales_order_item.product_id )join sales_order on sales_order_item.order_id = sales_order.id where order_time between ? and ? group by product_id;";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {
          stmt.setString(1,time1);
          stmt.setString(2,time2);
      ResultSet result = stmt.executeQuery();
      while (result.next()) {
            time.add(
              new TakeTimeRange(
                result.getLong("product_id"),
                result.getString("name"),
                result.getInt("SUM(amount)"),
                result.getString("TimeRangeOrder")));
      }

    }
    return time;
  }

  public int insert(SalesOrder order) throws Exception {
    int result = 0;
    String sql = "insert into sales_order (customer_id, order_time) values(?, ?)";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
      stmt.setInt(1, order.getCustomerId());
      stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
      result = stmt.executeUpdate();
      ResultSet key = stmt.getGeneratedKeys();
      int keyValue = 0;
      if (key.first()) {
        keyValue = key.getInt(1);
      }
      System.out.println("key:" + keyValue);
      System.out.println("size:" + order.getItems().size());
      int items = insertItems(keyValue, order.getItems());
      System.out.println("items inserted:" + items);
    } catch (Exception e) {
      throw e;
    }

    return result;
  }

  private int insertItems(int key, List<SalesOrderItem> items) throws Exception {
    int result = 0;
    String sqlItem = "insert into sales_order_item (order_id, product_id, amount, price) values(?,?, ?, ?)";
    for (SalesOrderItem item : items) {
      int price = 0;
      // get price from product table
      price = getPrice(item.getProductId());
      try (Connection conn = dataSource.getConnection();
          PreparedStatement stmtItem = conn.prepareStatement(sqlItem);) {
        stmtItem.setInt(1, key);
        stmtItem.setInt(2, item.getProductId());
        stmtItem.setInt(3, item.getAmount());
        stmtItem.setInt(4, price);
        result += stmtItem.executeUpdate();
      } catch (Exception e) {
        throw e;
      }
    } // for
    return result;
  }

  private int getPrice(int id) throws Exception {
    String sqlPrice = "select price from product where id = ?";
    int price = 0;
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement stmtPrice = conn.prepareStatement(sqlPrice);) {
      stmtPrice.setInt(1, id);
      try (ResultSet resultPrice = stmtPrice.executeQuery();) {
        if (resultPrice.next()) {
          price = resultPrice.getInt("price");
        }

      } catch (Exception e) {
        throw e;
      }
    } catch (Exception e) {
      throw e;
    }
    return price;
  }

}
