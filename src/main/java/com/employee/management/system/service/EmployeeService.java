package com.employee.management.system.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.employee.management.system.requestdto.EmployeeRequest;
import com.employee.management.system.responsedto.EmployeeResponse;
import com.employee.management.system.util.ResponseStructure;

public interface EmployeeService {

    ResponseEntity<ResponseStructure<EmployeeResponse>> createEmployee(EmployeeRequest employeeRequest);

    ResponseEntity<ResponseStructure<EmployeeResponse>> updateEmployee(Long employeeId, EmployeeRequest employeeRequest);

    ResponseEntity<ResponseStructure<EmployeeResponse>> getEmployeeById(Long employeeId);

    ResponseEntity<ResponseStructure<List<EmployeeResponse>>> getAllEmployees();

    ResponseEntity<ResponseStructure<String>> deleteEmployee(Long employeeId);
}
