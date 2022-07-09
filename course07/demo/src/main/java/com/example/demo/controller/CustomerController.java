package com.example.demo.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.Customer;
@RestController
public class CustomerController {
  @Autowired
  CustomerDAO dao;
  @GetMapping(value = "/customer")
  public List<Customer> retrieveCustomers() throws SQLException, Exception{
      return dao.findAll();
  }

  @GetMapping(value = {"/customer/{id}"})
  public Customer retrieveOneCustomer(@PathVariable("id") Long id) throws SQLException, Exception{
    Customer customer = dao.findOne(id);
    if (customer.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return customer;
  }

  @PostMapping(value = "/customer")
  public void processFormCreate(@RequestBody Customer customer) throws SQLException {
      dao.createCustomer(customer);
  }

  @PutMapping(value = "/customer/{id}")
  public void processFormUpdate(@RequestBody Customer customer, @PathVariable("id") Long id) throws SQLException {
    if (customer.getId() == null){
      customer.setId(id);
    }
    
    int result = dao.updateCustomer(customer);
    if (result == 0){
      throw new SQLException("id: "+id+" 並不存在");
    }
  }

  @DeleteMapping(value = "/customer/{id}")
  public void processFormDelete(@PathVariable("id") int id) throws SQLException {
    dao.deleteCustomer(id);
  }
}