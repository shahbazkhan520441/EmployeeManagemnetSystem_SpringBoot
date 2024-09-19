package com.employee.management.system.util;


import lombok.Getter;

@Getter
public class ResponseStructure<T> {
	
	private int statuscode;
	private String message;
	private T data;
	
	
	
	public ResponseStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}

	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	
	

}