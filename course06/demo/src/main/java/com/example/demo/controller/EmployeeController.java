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
  EmployeeDAO dao;
  @GetMapping(value = "/employee")
  public List<Employee> retrieveEmployees() throws SQLException{
      return dao.findAll();
  }

  @GetMapping(value = {"/employee/{id}"})
  public Employee retrieveOneEmployee(@PathVariable("id") Long id) throws SQLException{
    Employee employee = dao.findOne(id);
    if (employee.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return employee;
  }
}