package com.priyam.demo.dto;

public class EmployeeDTO {
	
	private Long employeeCode;
	private String fullName;
	
	
	public EmployeeDTO(Long employeeCode, String fullName) {
		super();
		this.employeeCode = employeeCode;
		this.fullName = fullName;
	}


	public Long getEmployeeCode() {
		return employeeCode;
	}


	public void setEmployeeCode(Long employeeCode) {
		this.employeeCode = employeeCode;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
	
	

}
