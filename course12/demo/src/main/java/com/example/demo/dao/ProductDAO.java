package com.example.demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductDAO {
  public List<Product> getList() throws Exception;

  public Product get(int id) throws Exception;

  public int insert(Product product) throws Exception;

  public int delete(int id) throws Exception;
}