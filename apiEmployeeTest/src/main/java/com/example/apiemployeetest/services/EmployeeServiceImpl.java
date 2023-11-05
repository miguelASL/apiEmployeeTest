package com.example.apiemployeetest.services;

import com.example.apiemployeetest.exception.ResourceNotFoundException;
import com.example.apiemployeetest.models.Employee;
import com.example.apiemployeetest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional //Para que se haga la transaccion de la base de datos y se haga el commit
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> saveEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (saveEmployee.isPresent()){
            throw new ResourceNotFoundException("El empleado exite con el correo : " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
