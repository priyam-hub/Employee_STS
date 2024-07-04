package com.priyam.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyam.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDeletedFalse();
	List<Employee> findByDeletedTrue();

}
