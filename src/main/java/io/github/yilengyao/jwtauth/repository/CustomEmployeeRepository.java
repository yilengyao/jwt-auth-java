package io.github.yilengyao.jwtauth.repository;

import io.github.yilengyao.jwtauth.model.Employee;

public interface CustomEmployeeRepository {
  Employee updateEmployee(String id, Employee employee);
}
