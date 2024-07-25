package com.priyam.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.priyam.demo.entity.Employee;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private EmployeeService employeeService;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("palpriyam95@gmail.com");
        message.setTo(to);
        message.setSubject(subject);

        Optional<Employee> employeeOptional = employeeService.findEmployeeByEmail(to);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            body = "Employee ID: " + employee.getEmployeeCode() + "\n\n" + body;
            body = "Dear " + employee.getFirstName() + "\n" + body;      
            
        }

        message.setText(body);
        javaMailSender.send(message);
    }
    
    
}
