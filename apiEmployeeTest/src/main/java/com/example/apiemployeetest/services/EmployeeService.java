package com.example.apiemployeetest.services;

import com.example.apiemployeetest.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Employee updateEmployee);
    void deleteEmployee(long id);
}
