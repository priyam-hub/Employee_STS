package com.priyam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyam.demo.entity.EmployeeContact;

public interface EmployeeContactRepository extends JpaRepository<EmployeeContact, Long> {

}
