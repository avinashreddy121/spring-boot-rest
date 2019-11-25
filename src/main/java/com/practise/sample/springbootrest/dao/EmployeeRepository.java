package com.practise.sample.springbootrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practise.sample.springbootrest.pojo.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	public Employee findByName(String name);
}
