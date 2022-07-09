package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Employee;
@Repository
public interface EmployeeDAO {
  public List<Employee> findAll();
  public Employee findOne(Long id) throws Exception;
  public int createEmployee(Employee employee);
  public int updateEmployee(Employee employee);
  public int deleteEmployee(Long id);
}