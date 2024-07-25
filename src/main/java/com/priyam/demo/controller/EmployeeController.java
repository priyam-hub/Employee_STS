package com.priyam.demo.controller;

//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyam.demo.dto.EmployeeDTO;
import com.priyam.demo.dto.SearchCriteria;
import com.priyam.demo.entity.EmailRequest;
import com.priyam.demo.entity.Employee;
import com.priyam.demo.entity.EmployeeContact;
import com.priyam.demo.service.EmailService;
import com.priyam.demo.service.EmployeeService;

import java.time.LocalDate;
import java.util.*;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
    private EmailService emailService;
	
	@PostMapping("/saveEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        employee.setActive(true); // Set default value for isActive
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
    
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        if (emailRequest == null || emailRequest.getTo() == null || emailRequest.getSubject() == null || emailRequest.getBody() == null) {
            return ResponseEntity.badRequest().body("Missing required fields in request body");
        }

        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok("Email sent successfully");
    }
    
    @GetMapping("/getEmployeesWithPagination")
    public Page<Employee> getEmployeesWithPagination(@RequestParam int page, @RequestParam int size) {
        return employeeService.getEmployeesWithPagination(page, size);
    }
        
    @GetMapping("/searchEmployees")
    public List<Employee> searchEmployees(
        @RequestParam(required = false) String employeeCode,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) Boolean isActive,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth) {
        
        return employeeService.searchEmployees(employeeCode, firstName, isActive, dateOfBirth);
    }
    
    @PostMapping("/searchEmployees")
    public Page<Employee> searchEmployees(@RequestBody SearchCriteria searchCriteria) {
        return employeeService.searchEmployees(searchCriteria);
    }

}
