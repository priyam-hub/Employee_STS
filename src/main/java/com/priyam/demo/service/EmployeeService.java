package com.priyam.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.priyam.demo.dto.EmployeeDTO;
import com.priyam.demo.entity.Employee;
import com.priyam.demo.entity.EmployeeContact;
import com.priyam.demo.repository.EmployeeContactRepository;
import com.priyam.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private EmployeeContactRepository employeeContactRepository;
	
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findByDeletedFalse();
	}
	
	public void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}
	
	public void softDeleteEmployeeById(Long id) {
		  Employee employee = employeeRepository.findById(id).get();
		  employee.setDeleted(true);
		  employeeRepository.save(employee);
	}
	
	public List<Employee> getDeletedEmployees() {
	    return employeeRepository.findByDeletedTrue();
	}
	
	public EmployeeContact createEmployeeContact(EmployeeContact employeeContact) {
        return employeeContactRepository.save(employeeContact);
    }

    public List<EmployeeContact> getAllEmployeeContacts() {
        return employeeContactRepository.findAll();
    }
    
    public Employee createEmployeeWithContact(Employee employee) {
        // Set the employee reference in each contact
        for (EmployeeContact contact : employee.getContacts()) {
            contact.setEmployee(employee);
        }
        return employeeRepository.save(employee);
    }
    
    public List<EmployeeDTO> getEmployeesByBirthdayMonth(int month) {
        return employeeRepository.findAll().stream()
            .filter(e -> e.getDateOfBirth().getMonthValue() == month)
            .map(e -> new EmployeeDTO(e.getEmployeeCode(), e.getFirstName() + " " + e.getLastName()))
            .collect(Collectors.toList());
    }
	

}
