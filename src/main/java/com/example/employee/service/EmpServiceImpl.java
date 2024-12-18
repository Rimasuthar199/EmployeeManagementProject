package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) throws RuntimeException {


            return employeeRepository.save(employee);


    }



    @Override
    public List<Employee> fetchEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee, Long empId) {

        Employee empDB = employeeRepository.findById(empId).get();

        if (Objects.nonNull(employee.getEmpname())  && !"".equalsIgnoreCase(employee.getEmpname())) {
            empDB.setEmpname(employee.getEmpname());
        }

        if (Objects.nonNull(employee.getEmail())  && !"".equalsIgnoreCase(employee.getEmail())) {
            empDB.setEmail(employee.getEmail());
        }

        if (Objects.nonNull(employee.getSalary())) {
            empDB.setSalary(employee.getSalary());
        }

        if(Objects.nonNull(employee.getDepartment())){
            empDB.setDepartment(employee.getDepartment());
        }

        return employeeRepository.save(empDB);
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        employeeRepository.deleteById(empId);
    }

}
