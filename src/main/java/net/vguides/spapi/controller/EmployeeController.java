package net.vguides.spapi.controller;

import java.lang.annotation.Repeatable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.vguides.spapi.exception.ResourceNotFoundException;
import net.vguides.spapi.model.Employee;
import net.vguides.spapi.repository.EmployeeRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all emps
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//create empoyee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee based on id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : "+id));
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails){
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : "+id));
		
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmailId(empDetails.getEmailId());
		
		Employee updEmp = employeeRepository.save(emp);
		return ResponseEntity.ok(updEmp);
	}
	
	//delete 
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : "+id));
		employeeRepository.delete(emp);
		Map<String, Boolean> resp = new HashMap<>();
		resp.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(resp);
		
	}
}
