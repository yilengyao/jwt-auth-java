package io.github.yilengyao.jwtauth.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.yilengyao.jwtauth.model.Employee;
import io.github.yilengyao.jwtauth.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Disabled
@Slf4j
@SpringBootTest
public class EmployeeServiceTest {

  @Autowired
  private EmployeeService employeeService;

  @Disabled
  @Test
  public void getEmployeeById() {
    String validEmployeeId = "65ade14b33200e155f2b6afe"; // Use the actual ID from your MongoDB Atlas

    Optional<Employee> employee = employeeService.getById(validEmployeeId);

    assertEquals(validEmployeeId, employee.get().getId());
    assertEquals("John", employee.get().getFirstname());
    assertEquals("Doe", employee.get().getLastname());
    assertEquals("johndoe@jmail.com", employee.get().getEmail());
  }

  @Disabled
  @Test
  public void getEmployeeByFirstname() {
    String firstname = "Walt";

    List<Employee> employees = employeeService.getByFirstname(firstname);

    assertEquals("Walt", employees.get(0).getFirstname());
    assertEquals("Walters", employees.get(0).getLastname());
    assertEquals("waltwalters@jmail.com", employees.get(0).getEmail());
  }

  @Disabled
  @Test
  public void getEmployeeByLastname() {
    String lastname = "Walters";

    List<Employee> employees = employeeService.getByLastname(lastname);

    assertEquals("Walt", employees.get(0).getFirstname());
    assertEquals("Walters", employees.get(0).getLastname());
    assertEquals("waltwalters@jmail.com", employees.get(0).getEmail());
  }

  @Disabled
  @Test
  public void getEmployeeByFirstnameAndLastname() {
    String firstname = "John";
    String lastname = "Doe";

    List<Employee> employees = employeeService.getByFirstnameAndLastname(firstname, lastname);
  
    assertEquals("John", employees.get(0).getFirstname());
    assertEquals("Doe", employees.get(0).getLastname());
    assertEquals("johndoe@jmail.com", employees.get(0).getEmail());
  }

  @Disabled
  @Test
  public void getEmployeeByEmail() {
    String email = "waltwalters@jmail.com";

    Optional<Employee> employee = employeeService.getByEmail(email);

    assertEquals("Walt", employee.get().getFirstname());
    assertEquals("Walters", employee.get().getLastname());
    assertEquals("waltwalters@jmail.com", employee.get().getEmail());
  }

  @Disabled
  @Test
  public void getAll() {
    List<Employee> employees = employeeService.getAll(0, 10);
    
    assertTrue(employees.size() > 0);
  }
  
  @Test
  @Disabled
  public void createEmployee() {
    Employee employee = new Employee();
    employee.setFirstname("Test");
    employee.setLastname("Test");
    employee.setEmail("test@jmail.com");

    Employee createdEmployee = employeeService.create(employee);
    log.info("Created employee: {}", createdEmployee);
  }

  @Test
  @Disabled
  public void updateEmployee() {
    String validEmployeeId = "6674c8f6431a3a10b24e389d"; 
    Employee employee = new Employee();
    employee.setFirstname("UpdatedTest");
    employee.setLastname("UpdatedTest");
    employee.setEmail("updatedtesttest@gmail.com");

    Employee updatedEmployee = employeeService.update(validEmployeeId, employee);
    log.info("Updated employee: {}", updatedEmployee);
  }

  @Test
  @Disabled
  public void deleteEmployee() {
    String validEmployeeId = "6674c4faa4c7145e571c8c8c"; 
    employeeService.delete(validEmployeeId);
  }
}
