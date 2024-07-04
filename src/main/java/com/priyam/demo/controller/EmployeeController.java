package com.priyam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyam.demo.dto.EmployeeDTO;
import com.priyam.demo.entity.Employee;
import com.priyam.demo.entity.EmployeeContact;
import com.priyam.demo.service.EmployeeService;

import java.util.*;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/saveEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return employee;
    }

    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @DeleteMapping("/deleteEmployee/{id}")
  	public String deleteStudent(@PathVariable Long id) {
    	employeeService.deleteEmployee(id);
    	return "Deleted Successfully";
  	}
    
    @DeleteMapping("/softDeleteEmployee/{id}")
    public void softDeleteEmployee(@PathVariable Long id) {
      employeeService.softDeleteEmployeeById(id);
    }
    
    @GetMapping("/getDeletedEmployees")
    public List<Employee> getDeletedEmployees() {
      return employeeService.getDeletedEmployees();
    }
    
    @PostMapping("/saveEmployeeContact")
    public EmployeeContact createEmployeeContact(@RequestBody EmployeeContact employeeContact) {
        return employeeService.createEmployeeContact(employeeContact);
    }

    @GetMapping("/getAllEmployeeContacts")
    public List<EmployeeContact> getAllEmployeeContacts() {
        return employeeService.getAllEmployeeContacts();
    }
    
    @PostMapping("/saveEmployeeWithContact")
    public Employee createEmployeeWithContact(@RequestBody Employee employee) {
        return employeeService.createEmployeeWithContact(employee);
    }
    
    @GetMapping("/getEmployeesByBirthdayMonth/{month}")
    public List<EmployeeDTO> getEmployeesByBirthdayMonth(@PathVariable int month) {
        return employeeService.getEmployeesByBirthdayMonth(month);
    }

}
