package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Employee;

@Repository
public interface EmployeeDAO {
  public List<Employee> findall();
}
