package com.practise.sample.springbootrest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practise.sample.springbootrest.dao.EmployeeRepository;
import com.practise.sample.springbootrest.pojo.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="/employee")
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepo;

	@ApiOperation(value =" Saving employee object to DB", notes = "Saves the given student object to DB and returns the link to that saved Resource")
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody List<Employee> employeeList) {
		for (Employee e:employeeList) {
			Employee savedEmployee = employeeRepo.save(e);
		}
		
		return ResponseEntity.ok().build();
	}

	@GetMapping("/employList")
	public List<Employee> employeeList() {
		return employeeRepo.findAll();
	}

	@GetMapping("/searchById/{id}")
	public Optional<Employee> searchById(@PathVariable Long id) {
		return employeeRepo.findById(id);

	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateById(@PathVariable long id,@RequestBody Employee employee){
		
		Optional<Employee> employeeOptional=employeeRepo.findById(id);
		/*
		 * if(!employeeOptional.isPresent()) { return ResponseEntity.notFound().build();
		 * }
		 */
		employee.setId(id);
		employeeRepo.save(employee);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/pbUpdate")
	 public ResponseEntity<Employee> updateData(@RequestBody String str){
		Employee string=employeeRepo.findByName(str);
		return new ResponseEntity<Employee>(string,HttpStatus.OK);
	}
	@PatchMapping("/partialUpdateEmployee/{id}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable long id, @RequestBody Employee employee){
		Optional<Employee> employeeOptional=employeeRepo.findById(id);
		if(!employeeOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		employee.setId(id);
		employeeRepo.save(employee);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id){
		employeeRepo.deleteById(id);;
		
		return ResponseEntity.ok().build();
	}
}
