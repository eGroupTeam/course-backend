package com.example.demo.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.AddressCount;
import com.example.demo.entity.Customer;
@RestController
public class CustomerController {
  @Autowired
  CustomerDAO dao;
  @GetMapping(value = "/customer")
  public List<Customer> retrieveCustomers() throws SQLException, Exception{
      return dao.getList();
  }

  @GetMapping(value = {"/customer/{id}"})
  public Customer retrieveOneCustomer(@PathVariable("id") Long id) throws SQLException, Exception{
    Customer customer = dao.get(id);
    if (customer.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return customer;
  }

  @GetMapping(value = {"/customer/name/{name}"})
  public List<Customer> retrieveOneCustomer(@PathVariable("name") String name) throws SQLException, Exception{
    List<Customer> customers = dao.getNameList(name);
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "name: "+name+" 並不存在");
    }
    return customers;
  }

  @GetMapping(value = {"/customer/order"})
  public List<Customer> retrieveCustomerOrderBy() throws SQLException, Exception{
    List<Customer> customers = dao.getWeightOrderedList();
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return customers;
  }

  @GetMapping(value = {"/customer/weight/{num1}/{num2}"})
  public List<Customer> retrieveCustomerWeightBetween(@PathVariable("num1") int num1,@PathVariable("num2") int num2) throws SQLException, Exception{
    List<Customer> customers = dao.getWeightBetweenList(num1, num2);
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return customers;
  }

  @GetMapping(value = {"/customer/address/distinct"})
  public List<String> retrieveCustomerAddressDistinct() throws SQLException, Exception{
    List<String> customers = dao.getDistinctAddressList();
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return customers;
  }

  @GetMapping(value = {"/customer/address/groupby"})
  public List<AddressCount> retrieveCustomerAddressGroupBy() throws SQLException, Exception{
    List<AddressCount> customers = dao.getGroupByAddressList();
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return customers;
  }

  @GetMapping(value = {"/customer/address/having/{count}"})
  public List<String> retrieveCustomerAddressHaving(@PathVariable("count") int count) throws SQLException, Exception{
    List<String> customers = dao.getGroupByAddressHavingList(count);
    if (customers.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return customers;
  }

  @PostMapping(value = "/customer")
  public void processFormCreate(@RequestBody Customer customer) throws Exception {
      dao.insert(customer);
  }

  @PutMapping(value = "/customer/{id}")
  public void processFormUpdate(@RequestBody Customer customer, @PathVariable("id") Long id) throws Exception {
    if (customer.getId() == null){
      customer.setId(id);
    }
    
    int result = dao.update(customer);
    if (result == 0){
      throw new SQLException("id: "+id+" 並不存在");
    }
  }
}