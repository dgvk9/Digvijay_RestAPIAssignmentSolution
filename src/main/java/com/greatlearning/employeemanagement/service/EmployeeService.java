package com.greatlearning.employeemanagement.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.greatlearning.employeemanagement.entity.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public Employee getEmployeeById(int id);

	public String addEmployee(Employee employee);
	
	public String updateEmployee(Employee employee);

	public String deletebyID(int id);
	
	public List<Employee> getEmployeesByName(String name);
	
	public List<Employee> getEmployeesSortedByName(Direction direction);
	

}
