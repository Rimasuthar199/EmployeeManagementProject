package com.example.employee.exception;

import com.example.employee.entity.Employee;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;

public class EmailConstriantException extends  RuntimeException{

     private Model model;
     private Employee employee;
    public EmailConstriantException(String message, Model model , Employee employee){
        super(message);
        this.employee = employee;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
