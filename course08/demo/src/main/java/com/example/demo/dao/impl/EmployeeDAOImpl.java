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
      rs.getInt("pay"));
  }

  public Employee get(Long id) throws Exception{
    Employee employee = new Employee(-1l,"","",0);
    String sql = "select * from employee where id = ?";
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
    List<Employee> employee = new ArrayList<Employee>();
    String sql = "select * from employee where name like ?";
    try (
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);){
      stmt.setString(1, "%"+name+"%");
      try (ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employee.add(getEmployee(rs));
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


  public List<Employee> getList() throws Exception{
    List<Employee> employee = new ArrayList<Employee>();
    String sql = "select * from employee";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employee.add(getEmployee(rs));
      }  
      
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public List<Employee> getDepartmentOrderedList() throws Exception{
    List<Employee> employee = new ArrayList<Employee>();
    String sql = "select * from employee order by department";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employee.add(getEmployee(rs));
      }
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public List<Employee> getPayBetweenList(int min, int max) throws Exception{
    List<Employee> employee = new ArrayList<Employee>();
    String sql = "select * from employee where pay between ? and ?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setInt(1,min);
      stmt.setInt(2,max);
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employee.add(getEmployee(rs));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public List<String> getDistinctDepartmentList() throws Exception{
    List<String> employee = new ArrayList<String>();
    String sql = "select distinct department from employee";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {
      while (rs.next()){
        employee.add(rs.getString("department"));
      }  
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public List<DepartmentCount> getGroupByDepartmentList() throws Exception{
    List<DepartmentCount> employee = new ArrayList<DepartmentCount>();
    String sql = "select department, COUNT(department) As count from employee group by department";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();) {     
      while (rs.next()){
        employee.add(new DepartmentCount(rs.getString("department"), rs.getInt("count")));
      }  
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public List<String> getGroupByDepartmentHavingList() throws Exception{
    List<String> employee = new ArrayList<String>();
    String sql = "select department from employee group by department having COUNT(department)=1";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      try(ResultSet rs = stmt.executeQuery();){
        while (rs.next()){
          employee.add(rs.getString("department"));
        }  
      }
      catch(Exception e) {throw e;}  
    } catch(Exception e) {
      throw e;
    }
    return employee;
  }

  public int insert(Employee employee) throws Exception{
    int result = 0;
    String sql = "insert into employee (name, department, pay) values(?, ?, ?)";
    try( 
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {    
      stmt.setString(1, employee.getName());
      stmt.setString(2, employee.getDepartment());
      stmt.setInt(3, employee.getPay());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      //something wrong
      throw e;
    }
    return result;
  }
  public int update(Employee employee) throws Exception{
    int result = 0;
    String sql = "update employee set name=?, department=?, pay=? where id =?";
    try(
      Connection conn = dataSource.getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);) {
      stmt.setString(1, employee.getName());
      stmt.setString(2, employee.getDepartment());
      stmt.setInt(3, employee.getPay());
      stmt.setLong(4, employee.getId());
      result = stmt.executeUpdate();
    } catch(Exception e) {
      throw e;
    }
    return result;
  }
}
