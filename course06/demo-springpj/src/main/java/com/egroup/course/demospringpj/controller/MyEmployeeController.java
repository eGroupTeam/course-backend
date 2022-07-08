package main.java.com.egroup.course.demospringpj.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import main.java.com.egroup.course.demospringpj.dao.EmployeeDAO;
import main.java.com.egroup.course.demospringpj.entity.Employee;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeDAO dao;

    @GetMapping(value = "/employee")
    public List<Employee> retrieveEmployees() throws SQLException {
        return dao.findAll();
    }

    String sql = "select * from employee where id = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);

    stmt.setLong(1,id);
    ResultSet rs = stmt.executeQuery();

    if(rs.next())
    {
        customer = getCustomer(rs);
    }

    @GetMapping(value = {"/employee/{id}"})
    public Employee retrieveOneCustomer(@PathVariable("id") Long id) {
        return dao.findOne(id);
    } throws SQLException
}
