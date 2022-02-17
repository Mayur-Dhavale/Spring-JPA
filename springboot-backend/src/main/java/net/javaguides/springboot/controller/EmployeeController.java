package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build create Employee Rest API
	//http://localhost:8080/api/employees
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
		
	}
	
	// get all Employee RestAPI
	//http://localhost:8080/api/employees
	@GetMapping
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployees();
		
	}
	
	//build get employee by id Rest API
	//http://localhost:8080/api/employees/3
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
		
	}
	
	//build update Employee REST API
	//http://localhost:8080/api/employees/3
	 @PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
		 }
	 
	 //build Delete Employee RestApi
	//http://localhost:8080/api/employees/3
	 @DeleteMapping("{id}")
	 public ResponseEntity<String> deleteEmployee(@PathVariable("id")long id){
		 employeeService.deleteEmployee(id);
		return new ResponseEntity<String>( "Employee Deleted Successfully DONE!", HttpStatus.OK);
		 
	 }
	
}
