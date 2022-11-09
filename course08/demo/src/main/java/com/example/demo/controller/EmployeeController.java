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

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.DepartmentCount;
import com.example.demo.entity.Employee;

@RestController
public class EmployeeController {
  @Autowired
  EmployeeDAO dao;
  @GetMapping(value = "/employee")
  public List<Employee> retrieveEmployees() throws SQLException, Exception{
      return dao.getList();
  }

  @GetMapping(value = {"/employee/{id}"})
  public Employee retrieveOneEmployee(@PathVariable("id") Long id) throws SQLException, Exception{
    Employee employee = dao.get(id);
    if (employee.getId() == -1){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在");
    }
    return employee;
  }

  @GetMapping(value = {"/employee/name/{name}"})
  public List<Employee> retrieveOneEmployee(@PathVariable("name") String name) throws SQLException, Exception{
    List<Employee> employees = dao.getNameList(name);
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "name: "+name+" 並不存在");
    }
    return employees;
  }

  @GetMapping(value = {"/employee/order"})
  public List<Employee> retrieveEmployeeOrderBy() throws SQLException, Exception{
    List<Employee> employees = dao.getDepartmentOrderedList();
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return employees;
  }

  @GetMapping(value = {"/employee/wage/{num1}/{num2}"})
  public List<Employee> retrieveEmployeeWageBetween(@PathVariable("num1") int num1,@PathVariable("num2") int num2) throws SQLException, Exception{
    List<Employee> employees = dao.getWageBetweenList(num1, num2);
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return employees;
  }

  @GetMapping(value = {"/employee/department/distinct"})
  public List<String> retrieveEmployeeDepartmentDistinct() throws SQLException, Exception{
    List<String> employees = dao.getDistinctDepartmentList();
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return employees;
  }

  @GetMapping(value = {"/employee/department/groupby"})
  public List<DepartmentCount> retrieveEmployeeDepartmentGroupBy() throws SQLException, Exception{
    List<DepartmentCount> employees = dao.getGroupByDepartmentList();
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return employees;
  }

  @GetMapping(value = {"/employee/department/having"})
  public List<String> retrieveEmployeeDepartmentHaving() throws SQLException, Exception{
    List<String> employees = dao.getGroupByDepartmentHavingList();
    if (employees.size() == 0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    return employees;
  }

  @PostMapping(value = "/employee")
  public void processFormCreate(@RequestBody Employee employee) throws Exception {
      dao.insert(employee);
  }

  @PutMapping(value = "/employee/{id}")
  public void processFormUpdate(@RequestBody Employee employee, @PathVariable("id") Long id) throws Exception {
    if (employee.getId() == null){
      employee.setId(id);
    }
    
    int result = dao.update(employee);
    if (result == 0){
      throw new SQLException("id: "+id+" 並不存在");
    }
  }
}