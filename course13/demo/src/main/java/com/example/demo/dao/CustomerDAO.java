package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AddressCount;
import com.example.demo.entity.Customer;
@Repository
public interface CustomerDAO {
  public List<Customer> getList() throws Exception;
  public List<Customer> getNameList(String name) throws Exception;
  public List<Customer> getWeightOrderedList() throws Exception;
  public List<Customer> getWeightBetweenList(int lowBound, int upperBound) throws Exception;
  public List<String> getDistinctAddressList() throws Exception;
  public List<AddressCount> getGroupByAddressList() throws Exception;
  public List<String> getGroupByAddressHavingList() throws Exception;
  public Customer get(Long id) throws Exception;
  public int insert(Customer customer) throws Exception;
  public int update(Customer customer) throws Exception;
  public int delete(Long id) throws Exception;
}