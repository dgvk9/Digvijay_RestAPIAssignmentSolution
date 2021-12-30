package com.greatlearning.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagement.entity.Employee;
import com.greatlearning.employeemanagement.service.EmployeeService;

@RestController
public class EmployeeController {
	
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getAllEmployees")
	public List<Employee> hello() {
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/addEmployee")
	public String addEmployee(Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	
	@GetMapping("/getEmployeeById")
	public Employee getEmployeeById(int id) {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/getEmployeesByName")
	public List<Employee> getEmployeesByName(String name){
		return employeeService.getEmployeesByName(name);
	}
	
	@GetMapping("/getEmployeesSortedByName")
	public List<Employee> getEmployeesSortedByName(Direction direction){
		return employeeService.getEmployeesSortedByName(direction);
	}
	
	@DeleteMapping("/deletebyID")
	public String deletebyID(int id) {
		return employeeService.deletebyID(id);
	}
	
	@PutMapping("/updateEmployee")
	public String updateEmployee(Employee employee) {
		return employeeService.updateEmployee(employee);
	}

}
