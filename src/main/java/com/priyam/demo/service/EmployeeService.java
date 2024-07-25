package com.priyam.demo.service;

import java.time.LocalDate;
//import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.priyam.demo.dto.EmployeeDTO;
import com.priyam.demo.dto.SearchCriteria;
import com.priyam.demo.entity.Employee;
import com.priyam.demo.entity.EmployeeContact;
import com.priyam.demo.repository.EmployeeContactRepository;
import com.priyam.demo.repository.EmployeeRepository;
import com.priyam.demo.specification.EmployeeSpecification;

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
    
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    public Page<Employee> getEmployeesWithPagination(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }
    
    public List<Employee> searchEmployees(String employeeCode, String firstName, Boolean isActive, LocalDate dateOfBirth) {
        Specification<Employee> spec = Specification.where(EmployeeSpecification.hasEmployeeCode(employeeCode))
            .and(EmployeeSpecification.hasFirstName(firstName))
            .and(EmployeeSpecification.isActive(isActive))
            .and(EmployeeSpecification.hasDateOfBirth(dateOfBirth));
        
        return employeeRepository.findAll(spec);
    }
    
    public Page<Employee> searchEmployees(SearchCriteria searchCriteria) {
        Specification<Employee> spec = Specification.where(EmployeeSpecification.hasEmployeeCode(searchCriteria.getEmployeeCode()))
            .and(EmployeeSpecification.hasFirstName(searchCriteria.getFirstName()))
            .and(EmployeeSpecification.isActive(searchCriteria.getIsActive()))
            .and(EmployeeSpecification.hasDateOfBirth(searchCriteria.getDateOfBirth()));
        
        Sort sort = Sort.by(Sort.Direction.fromString(searchCriteria.getSortDirection()), searchCriteria.getSortProperty());
        Pageable pageable = PageRequest.of(searchCriteria.getPage(), searchCriteria.getSize(), sort);
        return employeeRepository.findAll(spec, pageable);
    }
    
    
}
