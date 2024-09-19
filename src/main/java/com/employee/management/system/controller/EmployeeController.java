package com.employee.management.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.system.requestdto.EmployeeRequest;
import com.employee.management.system.responsedto.EmployeeResponse;
import com.employee.management.system.service.EmployeeService;
import com.employee.management.system.util.ResponseStructure;




import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/employees")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
		return employeeService.createEmployee(employeeRequest);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<EmployeeResponse>> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeRequest employeeRequest) {
		return employeeService.updateEmployee(id, employeeRequest);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}
}

