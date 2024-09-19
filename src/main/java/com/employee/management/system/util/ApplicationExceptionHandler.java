package com.employee.management.system.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.management.system.exception.EmployeeNotFoundException;
import com.employee.management.system.exception.InvalidRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause) {
        return ResponseEntity.status(status)
                .body(new ErrorStructure<String>()
                        .setStatus(status.value())
                        .setMessage(errorMessage)
                        .setRootCause(rootCause));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Employee not found", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleInvalidRequestExceptionException(InvalidRequestException ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "Invalid request :", ex.getMessage());
    }

  

    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handleCourseNotFoundException(Exception ex) {
        return errorResponse(HttpStatus.NOT_FOUND, "invalid request", ex.getMessage());
    }

   
}
