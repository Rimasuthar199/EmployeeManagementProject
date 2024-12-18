package com.example.employee.controller;

import com.example.employee.entity.ConfirmationForm;
import com.example.employee.entity.Department;
import com.example.employee.entity.Employee;
import com.example.employee.exception.EmailConstriantException;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.DepartmentService;
import com.example.employee.service.EmpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employee")
    public String getAllEmployee(Model model){
        List<Employee> employeeList = empService.fetchEmployeeList();
        model.addAttribute("employees", employeeList);
        model.addAttribute("employee", new Employee());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList",departmentList);
        return "index";
    }

    @GetMapping("/employeepersist")
    private String changeEmployee(Model model){
        model.addAttribute("employee", new Employee());
        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList",departmentList);
        return "addemp";
    }

    @PostMapping("/employeepersist")
    public String saveEmployee(@Valid @ModelAttribute("employee")Employee employee , BindingResult result, Model model, RedirectAttributes redirectAttributes) throws DataIntegrityViolationException{

        if (result.hasErrors()) {
            redirectAttributes.addAttribute("hasErrors", true);
            model.addAttribute("employee", employee);
            List<Department> departmentList = departmentService.getAllDepartments();
            model.addAttribute("departmentList",departmentList);


            List<String> data = result.getAllErrors()
                    .stream()
                    .map(error -> {
                        var defaultMessage = error.getDefaultMessage();
                        if (error instanceof FieldError) {
                            var fieldError = (FieldError) error;
                            return defaultMessage;
                        } else {
                             return  defaultMessage;
                        }
                    }).collect(Collectors.toList());
            model.addAttribute("error", data.get(0));
            return "addemp";
        }
        try {
            empService.saveEmployee(employee);
        }catch (DataIntegrityViolationException ex){
            throw new EmailConstriantException(" EmailId Already Registered ",model,employee);
        }
        model.addAttribute("employee", new Employee());
        return "redirect:/employee";
    }

    @PostMapping("/employeeput")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, Model model, BindingResult result)
    {
         if (result.hasErrors()){
             System.out.println("Error");
         }
        model.addAttribute("employee", new Employee());
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());

        // checking employee exist or not
        if (existingEmployee.isPresent()) {
            empService.updateEmployee(employee, employee.getId());
        } else {
            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
        }
        return "redirect:/employee";
    }



    @PostMapping("/empremove")
    public String deleteEmployeeById(Employee employee, Model model)
    {

        model.addAttribute("employee", new Employee());
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            empService.deleteEmployeeById(employee.getId());
        }
        return "redirect:/employee";


    }
}
