package com.example.apiemployeetest.models;

import com.example.apiemployeetest.repositories.EmployeeRepository;
import com.example.apiemployeetest.services.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
class EmployeeTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("Miguel")
                .lastName("Sarmiento")
                .email("miguel@miau.com")
                .build();
    }

    @Test
    @DisplayName("Test for save employee")
    public void saveEmployeeTest(){
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee))
                .willReturn(employee);
        // when - action
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // then - verify
        assertNotNull(savedEmployee);
    }

    @Test
    @DisplayName("Junit test for List employee")
    void givenEmployeeList() {

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Moshe")
                .lastName("El Barbaro")
                .email("moshe@miau.com")
                .build();
        given(employeeRepository.findAll())
                .willReturn(List.of(employee.employee1));

        // when - action
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        assertAll(
                () -> assertNotNull(employeeList),
                () -> assertEquals(2, employeeList.size())
        );
        assertNotNull(employeeList);
        assertEquals(2, employeeList.size());
    }
    @Test
    @DisplayName("Junit test for update")
    void givenEmployeeObjeect_WhenUpdate(){
        given(employeeRepository.save(employee))
                .willReturn(employee);
        employee.setEmail("ram@mail.com");
        employee.setFirstName("Jose");

        // when - action
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        //then - verify the output
        assertAll(
                () -> assertEquals(updatedEmployee.getEmail(),"ram@mail.com"),
                () -> assertEquals(updatedEmployee.getFirstName(), "Jose")
        );
    }

    @Test
    @DisplayName("Junit test for employee delete")
    void givenEmployeeIdWhenDelete(){
        long employeeId = 1L;
        willDoNothing().given(employeeRepository).deleteById(employeeId);

        // when - action
        employeeService.deleteEmployee(employeeId);

        // then - verify the output
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(employee.getId());
    }
}