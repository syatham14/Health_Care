package com.healthcare.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class GenericResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
