package com.example.employee.exception;

import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import com.example.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
@Controller
@ControllerAdvice
public class GlobalExceptionHandler implements ErrorController {
//extends ResponseEntityExceptionHandler
    @Autowired
    private  DepartmentService departmentService;

    @ExceptionHandler(EmailConstriantException.class)
    public String handle(EmailConstriantException ex, Model model){

        model.addAttribute("employee", ex.getEmployee());
        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("error", ex.getMessage());
        return "addemp";
    }



}
