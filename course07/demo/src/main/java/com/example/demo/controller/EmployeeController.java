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

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
@RestController
public class EmployeeController {
  @Autowired
  EmployeeDAO dao;
  @GetMapping(value = "/employee")
  public List<Employee> retrieveEmployees() throws SQLException, Exception{
      return dao.findAll();
  }

  @GetMapping(value = {"/employee/{id}"})
  public Employee retrieveOneEmployee(@PathVariable("id") Long id) throws SQLException, Exception{
    Employee employee = dao.findOne(id);
    if (employee.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return employee;
  }

  @PostMapping(value = "/employee")
  public void processFormCreate(@RequestBody Employee employee) throws SQLException {
      dao.createEmployee(employee);
  }

  @PutMapping(value = "/employee/{id}")
  public void processFormUpdate(@RequestBody Employee employee, @PathVariable("id") Long id) throws SQLException {
    if (employee.getId() == null){
      employee.setId(id);
    }
    
    int result = dao.updateEmployee(employee);
    if (result == 0){
      throw new SQLException("id: "+id+" 並不存在");
    }
  }

  @DeleteMapping(value = "/employee/{id}")
  public void processFormDelete(@PathVariable("id") int id) throws SQLException {
    dao.deleteEmployee(id);
  }
}