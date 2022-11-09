package com.example.demo.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.DepartmentCount;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
  @Autowired
  private DataSource dataSource;
  
  private Employee getEmployee(ResultSet rs) throws SQLException, Exception{
    return new Employee(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getString("department"),
      rs.getInt("wage"));
  }

  public Employee get(Long id) throws Exception{
    Employee employee = new Employee(-1l,"","",1);
    String sql = "select id, name, department, wage from employee where id = ?";
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ){
      stmt.setLong(1, id);
      try (ResultSet rs = stmt.executeQuery();){
        if (rs.next()){
          employee=getEmployee(rs);
        } 
      }
      catch (Exception e){
        throw e;
      }
    }
    catch (Exception e){
      throw e;
    }
    return employee;

  }

  public List<Employee> getNameList(String name) throws Exception{
    List<Employee> employees = new ArrayList<Employee>();
    String sql = "select id, name, department, wage from employee where name like ?";
    //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);){
      stmt.setString(1, "%"+name+"%");
      try (ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employees.add(getEmployee(rs));
        } 
      }
      catch (Exception e){
        throw e;
      }
    }
    catch (Exception e){
      throw e;
    }
    return employees;

  }


  public List<Employee> getList() throws Exception{
    List<Employee> employees = new ArrayList<Employee>();
    String sql = "select id, name, department, wage from employee";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employees.add(getEmployee(rs));
      }  
      
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public List<Employee> getDepartmentOrderedList() throws Exception{
    List<Employee> employees = new ArrayList<Employee>();
    String sql = "select id, name, department, wage from employee order by department";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employees.add(getEmployee(rs));
      }
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public List<Employee> getWageBetweenList(int lowBound, int upperBound) throws Exception{
    List<Employee> employees = new ArrayList<Employee>();
    String sql = "select id, name, department, wage from employee where wage between ? and ?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1,lowBound);
      stmt.setInt(2,upperBound);
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employees.add(getEmployee(rs));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public List<String> getDistinctDepartmentList() throws Exception{
    List<String> employees = new ArrayList<String>();
    String sql = "select distinct department from employee";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employees.add(rs.getString("department"));
      }  
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public List<DepartmentCount> getGroupByDepartmentList() throws Exception{
    List<DepartmentCount> employees = new ArrayList<DepartmentCount>();
    String sql = "select department, COUNT(department) As count from employee group by department";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {     
      while (rs.next()){
        employees.add(new DepartmentCount(rs.getString("department"), rs.getInt("count")));
      }  
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public List<String> getGroupByDepartmentHavingList() throws Exception{
    List<String> employees = new ArrayList<String>();
    String sql = "select department from employee group by department having COUNT(department)=1";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employees.add(rs.getString("department"));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return employees;
  }

  public int insert(Employee employee) throws Exception{
    int result = 0;
    String sql = "insert into employee (name, department, wage) values(?, ?, ?)";
    try( 
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {    
      stmt.setString(1, employee.getName());
      stmt.setString(2, employee.getDepartment());
      stmt.setInt(3, employee.getWage());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      //something wrong
      throw e;
    }
    return result;
  }
  public int update(Employee employee) throws Exception{
    int result = 0;
    String sql = "update employee set name=?, department=?, wage=? where id =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, employee.getName());
      stmt.setString(2, employee.getDepartment());
      stmt.setInt(3, employee.getWage());
      stmt.setLong(4, employee.getId());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    return result;
  }
}
