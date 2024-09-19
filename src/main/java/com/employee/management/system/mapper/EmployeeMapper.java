package com.employee.management.system.mapper;

import org.springframework.stereotype.Component;

import com.employee.management.system.entity.Employee;
import com.employee.management.system.requestdto.EmployeeRequest;
import com.employee.management.system.responsedto.EmployeeResponse;

@Component
public class EmployeeMapper {
    
    public Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .name(employeeRequest.getName())
                .gender(employeeRequest.getGender())
                .departments(employeeRequest.getDepartments())
                .startDate(employeeRequest.getStartDate())
                .notes(employeeRequest.getNotes())
                .salary(employeeRequest.getSalary())
                .build();
    }

    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .employeeid(employee.getEmployeeid())
                .name(employee.getName())
                .gender(employee.getGender())
                .departments(employee.getDepartments())
                .startDate(employee.getStartDate())
                .notes(employee.getNotes())
                .salary(employee.getSalary())
                .build();
    }
}
