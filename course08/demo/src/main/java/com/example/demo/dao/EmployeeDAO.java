package com.example.demo.dao;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DepartmentCount;
import com.example.demo.entity.Employee;
@Repository
public interface EmployeeDAO {
  public List<Employee> getList() throws Exception;
  public List<Employee> getNameList(String name) throws Exception;
  public List<Employee> getDepartmentOrderedList() throws Exception;
  public List<Employee> getWageBetweenList(int lowBound, int upperBound) throws Exception;
  public List<String> getDistinctDepartmentList() throws Exception;
  public List<DepartmentCount> getGroupByDepartmentList() throws Exception;
  public List<String> getGroupByDepartmentHavingList() throws Exception;
  public Employee get(Long id) throws Exception;
  public int insert(Employee employee) throws Exception;
  public int update(Employee employee) throws Exception;
}