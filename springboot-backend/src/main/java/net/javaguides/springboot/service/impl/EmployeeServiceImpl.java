package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	

	private EmployeeRepository employeeRepository;
	
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


// API 1 save the employee
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}


//API 2 get all employee
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}


// API 3 GET BY ID
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
			
		} else {
			throw new ResourceNotFoundException("Employee","Id", id);

		}
		//By USING Labada Expression
		//return employeeRepository.findById(id).orElseThrow(()->newResourceNotFoundException("Employee","Id", id) )
		
	
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//first we have to check give id exit in DB or not
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existingEmployee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}



	@Override
	public void deleteEmployee(long id) {
		//first check employee exist DB or not
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		employeeRepository.deleteById(id);
		
	}

}

