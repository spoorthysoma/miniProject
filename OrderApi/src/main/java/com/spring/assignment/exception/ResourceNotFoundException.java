package com.spring.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 instead of adding an annotation on every RESTapi that returns a status
 adding a response status annotation to the exception class itself.
 whenever the RESTapi throws ResourceNotFoundException.The NOT_FOUND status is returned to the client.
 
  It's a custom exception class
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND) // indicate that it should result in an HTTP 404 status code when thrown in a Spring MVC or Spring Boot application.
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String fieldName;
	private int id;

	// object type is given as the field maybe be of any type.
	private Object fieldValue;

	public ResourceNotFoundException(String name, String fieldName, Object fieldValue) {
		super(String.format("+++++++++++++ %s not found with %s : %s+++++++++++++ ",name,fieldName,fieldValue));
		this.name = name;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(int id) {
		super(String.format("++++++++++++++%d not found with++++++++++++++",id));
		this.setId(id);
		
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
