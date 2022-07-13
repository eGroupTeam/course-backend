package com.example.demo.controller;
//import java.util.ArrayList;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entity.Employee;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.DepartmentCount;

@RestController
public class EmployeeController {

  private ArrayList<Employee> employeeList = new ArrayList<>();

  public EmployeeController(){
    employeeList.add(new Employee("Mary", "IT"));
    employeeList.add(new Employee("Ruby", "IT"));
    employeeList.add(new Employee("Ben", "IT"));
  }
   

  @GetMapping("/employee")
  public ArrayList<Employee> get() {
    if (employeeList.size()==0){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "無資料");
    }
    
    return employeeList;
  }
/*
  @GetMapping("/employee/{id}")
  public Employee retrieveOneEmployee(@PathVariable("id") int id){
    return employeeList.get(id);
  }
 */


  @GetMapping("/employee/{id}")
  public Employee retrieveOneEmployee(@PathVariable("id") int id){
    Employee employee= new Employee("", "");
    try{
      employee =  employeeList.get(id);
    }
    
    catch(IndexOutOfBoundsException exception){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在", exception);

    }
     
    catch(Exception e){
      //employee.setName("id並不存在");
      System.out.println(e);

    }
    return employee;
     
  }
 
  @PostMapping("/employee")
  public void addOneEmployee(@RequestBody Employee employee) {
    employeeList.add(employee);
  }

  @PutMapping("/employee/{id}")
  public void editOneEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
    try{
      employeeList.set(id, employee);
    }
    catch(IndexOutOfBoundsException exception){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在", exception);
    }
    
  }

  @DeleteMapping("/employee/{id}")
  public void removeOneEmployee(@PathVariable("id") int id) {
    try{
      employeeList.remove(id);
    }
    catch(IndexOutOfBoundsException exception){
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "id: "+id+" 並不存在", exception);
    }
    
  }

}
