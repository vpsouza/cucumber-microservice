package com.buscapecompany.poc.cucumbermicroservice.exception;

import java.util.List;

import com.buscapecompany.poc.cucumbermicroservice.validation.ValidationResult;

public class ProductException extends Exception {

	private static final long serialVersionUID = -4997087431246706534L;
	private String errorMessage;
	private List<ValidationResult.Violation> violations = null;
	
	public ProductException(String errorMessage){
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public ProductException(List<ValidationResult.Violation> violations){
		super();
		this.violations = violations;
	}
	
	public ProductException() {
		super();
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public List<ValidationResult.Violation> getViolations() {
		return this.violations;
	}
}
