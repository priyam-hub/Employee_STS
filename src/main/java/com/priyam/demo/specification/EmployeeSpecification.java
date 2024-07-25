package com.priyam.demo.specification;

import com.priyam.demo.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecification {
	
	public static Specification<Employee> hasEmployeeCode(String employeeCode) {
        return (root, query, criteriaBuilder) -> 
            employeeCode == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("employeeCode"), employeeCode);
    }

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> 
            firstName == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Employee> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> 
            isActive == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    public static Specification<Employee> hasDateOfBirth(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) -> 
            dateOfBirth == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth);
    }

}
