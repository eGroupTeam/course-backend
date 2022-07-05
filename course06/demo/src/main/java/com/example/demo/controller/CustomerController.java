package com.example.demo.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.Customer;
@RestController
public class CustomerController {
  @Autowired
  CustomerDAO dao;
  @GetMapping(value = "/customer")
  public List<Customer> retrieveCustomers() throws SQLException{
      return dao.findAll();
  }

  @GetMapping(value = {"/customer/{id}"})
  public Customer retrieveOneCustomer(@PathVariable("id") Long id) throws SQLException{
    Customer customer = dao.findOne(id);
    if (customer.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return customer;
  }
}