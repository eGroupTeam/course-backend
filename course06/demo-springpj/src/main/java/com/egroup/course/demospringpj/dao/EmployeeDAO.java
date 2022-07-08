package main.java.com.egroup.course.demospringpj.dao;
import java.util.List;
import com.egroup.course.demospringpj.entity.Employee;
public interface EmployeeDAO {
  public List<Employee> findAll();
  public Employee findOne(Long id);
}