package com.employee.management.system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {
   private String message;
}