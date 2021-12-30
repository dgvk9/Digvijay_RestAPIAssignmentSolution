package com.greatlearning.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.entity.Employee;
import com.greatlearning.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).get();
	}

	@Override
	public String addEmployee(Employee employee) {
		employeeRepository.save(employee);
		employeeRepository.flush();
		return "Employee has been added/updated";
		
	}

	@Override
	public String deletebyID(int id) {
		employeeRepository.deleteById(id);
		return "Employee record has been deleted";
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		Employee employeesWithName = new Employee();
		employeesWithName.setFirstName(name);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("firstName", 
				ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("id","lastName","email");
		Example<Employee> example = Example.of(employeesWithName, exampleMatcher );
		return employeeRepository.findAll(example);
	}

	@Override
	public List<Employee> getEmployeesSortedByName(Direction direction) {
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

	@Override
	public String updateEmployee(Employee employee) {
		Employee employeeToUpdate = employeeRepository.findById(employee.getId()).get();
		employeeToUpdate.setFirstName(employee.getFirstName());
		employeeToUpdate.setLastName(employee.getLastName());
		employeeToUpdate.setEmail(employee.getEmail());
		employeeRepository.save(employeeToUpdate);
		employeeRepository.flush();
		return "Employee has been added/updated";
	}


}
