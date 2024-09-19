package com.employee.management.system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class InvalidRequestException extends RuntimeException {
	
private String message;

}
