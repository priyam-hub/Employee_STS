package com.priyam.demo.repository;

//import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.priyam.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	
	List<Employee> findByDeletedFalse();
	List<Employee> findByDeletedTrue();
	Optional<Employee> findByEmail(String email);
	
	// Add the method for pagination
    Page<Employee> findAll(Pageable pageable);

}
