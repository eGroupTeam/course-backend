package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Customer;
@Repository
public interface CustomerDAO {
  public List<Customer> findAll();
  public Customer findOne(Long id) throws Exception;
  public int createCustomer(Customer customer);
  public int updateCustomer(Customer customer);
  public int deleteCustomer(int id);
}