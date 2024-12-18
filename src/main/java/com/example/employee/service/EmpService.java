package com.example.employee.service;

import com.example.employee.entity.Employee;

import java.util.List;

public interface EmpService {

    Employee saveEmployee(Employee  employee) ;

    List<Employee> fetchEmployeeList();
    Employee updateEmployee(Employee department,Long empId);
    // Delete operation
    void deleteEmployeeById(Long empId);
}
