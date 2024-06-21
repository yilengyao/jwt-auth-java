package io.github.yilengyao.jwtauth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.yilengyao.jwtauth.model.Employee;
import io.github.yilengyao.jwtauth.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {
  
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getById(@PathVariable String id) {
    Optional<Employee> employee = employeeService.getById(id);
    return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getEmployees(
      @RequestParam(required = false) String firstname,
      @RequestParam(required = false) String lastname,
      @RequestParam(required = false) String email,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {

    if (firstname != null && lastname != null) {
      return ResponseEntity.ok(employeeService.getByFirstnameAndLastname(firstname, lastname));
    } else if (firstname != null) {
      return ResponseEntity.ok(employeeService.getByFirstname(firstname));
    } else if (lastname != null) {
      return ResponseEntity.ok(employeeService.getByLastname(lastname));
    } else if (email != null) {
      return employeeService.getByEmail(email)
        .map(List::of)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    } else {
      return ResponseEntity.ok(employeeService.getAll(pageNumber, pageSize));
    }
  }

  @PostMapping
  public ResponseEntity<Employee> create(@RequestBody Employee employee) {
    Employee createdEmployee = employeeService.create(employee);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
  }

  @PutMapping
  public ResponseEntity<Employee> update(@RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.update(employee.getId(), employee);
    return ResponseEntity.ok(updatedEmployee);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<Employee> employee = employeeService.getById(id);
    if (!employee.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT)
        .body("No employee matches ID " + id);
    }
    employeeService.delete(id);
    return (ResponseEntity.ok("Employee with ID " + id + " was deleted."));
  }
}
