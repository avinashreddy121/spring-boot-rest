package com.practise.sample.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.practise.sample.springbootrest.dao.EmployeeRepository;

public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	
}
