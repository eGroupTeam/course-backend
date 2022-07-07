package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Customer;
@Repository
public interface CustomerDAO {
  public List<Customer> findAll();
  public Customer findOne(Long id);
}