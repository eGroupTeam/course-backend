package com.egroup.course.demospringpj.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.egroup.course.demospringpj.dao.EmployeeDAO;
import com.egroup.course.demospringpj.entity.Employee;

@RestController
public class MyEmployeeController {
    @Autowired
    EmployeeDAO dao;  //DAO=Data Access Object

    @GetMapping(value = "/employee")
    public List<Employee> retrieveEmployees() throws SQLException {
        return dao.findAll();
    }

    @GetMapping(value = {"/employee/{id}"}) 
    public Employee retrieveOneCustomer(@PathVariable("id") Long id) throws SQLException{
        Employee e =dao.findOne(id);
        if (e.getId()== -1){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "id: "+id+" 並不存在"
            );
        }
        return e;
    }
}
