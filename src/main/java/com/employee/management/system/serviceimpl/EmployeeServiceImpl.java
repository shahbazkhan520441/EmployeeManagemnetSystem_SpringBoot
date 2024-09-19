package com.employee.management.system.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.management.system.entity.Employee;
import com.employee.management.system.exception.EmployeeNotFoundException;
import com.employee.management.system.exception.InvalidRequestException;
import com.employee.management.system.mapper.EmployeeMapper;
import com.employee.management.system.repository.EmployeeRepository;
import com.employee.management.system.requestdto.EmployeeRequest;
import com.employee.management.system.responsedto.EmployeeResponse;
import com.employee.management.system.service.EmployeeService;
import com.employee.management.system.util.ResponseStructure;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	@Override
	public ResponseEntity<ResponseStructure<EmployeeResponse>> createEmployee(EmployeeRequest employeeRequest) {
		validateEmployeeRequest(employeeRequest);
		Employee employee = employeeMapper.mapEmployeeRequestToEmployee(employeeRequest);
		Employee savedEmployee = employeeRepository.save(employee);
		EmployeeResponse employeeResponse = employeeMapper.mapEmployeeToEmployeeResponse(savedEmployee);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<EmployeeResponse>()
						.setStatuscode(HttpStatus.CREATED.value())
						.setMessage("Employee created successfully")
						.setData(employeeResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<EmployeeResponse>> updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
		return employeeRepository.findById(employeeId)
				.map(employee -> {
					updateEmployeeDetails(employee, employeeRequest);
					Employee updatedEmployee = employeeRepository.save(employee);
					EmployeeResponse employeeResponse = employeeMapper.mapEmployeeToEmployeeResponse(updatedEmployee);

					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseStructure<EmployeeResponse>()
									.setStatuscode(HttpStatus.OK.value())
									.setMessage("Employee updated successfully")
									.setData(employeeResponse));
				})
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
	}

	@Override
	public ResponseEntity<ResponseStructure<EmployeeResponse>> getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
		EmployeeResponse employeeResponse = employeeMapper.mapEmployeeToEmployeeResponse(employee);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<EmployeeResponse>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("Employee found")
						.setData(employeeResponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<EmployeeResponse>>> getAllEmployees() {
		List<EmployeeResponse> employeeResponses = employeeRepository.findAll().stream()
				.map(employeeMapper::mapEmployeeToEmployeeResponse)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<List<EmployeeResponse>>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("All employees retrieved")
						.setData(employeeResponses));
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + employeeId));
		employeeRepository.delete(employee);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<String>()
						.setStatuscode(HttpStatus.OK.value())
						.setMessage("Employee deleted successfully")
						.setData("Deleted"));
	}

	private void validateEmployeeRequest(EmployeeRequest employeeRequest) {
		if (employeeRequest.getName() == null || employeeRequest.getName().trim().isEmpty()) {
			throw new InvalidRequestException("Employee name cannot be empty");
		}
	}

	private void updateEmployeeDetails(Employee employee, EmployeeRequest employeeRequest) {
		employee.setName(employeeRequest.getName());
		employee.setGender(employeeRequest.getGender());
		employee.setDepartments(employeeRequest.getDepartments());
		employee.setStartDate(employeeRequest.getStartDate());
		employee.setNotes(employeeRequest.getNotes());
		employee.setSalary(employeeRequest.getSalary());
	}
}
