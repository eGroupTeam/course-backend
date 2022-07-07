package com.example.demo.controller;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
@RestController
public class EmployeeController {
  @Autowired
  EmployeeDAO Dao;
  @GetMapping(value = "/employee")
  public List<Employee> retrieveEmployees() throws SQLException{
      return Dao.findall();
  }

}