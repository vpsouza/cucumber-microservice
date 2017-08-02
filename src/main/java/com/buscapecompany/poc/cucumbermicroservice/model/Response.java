package com.buscapecompany.poc.cucumbermicroservice.model;

import io.swagger.annotations.ApiModelProperty;

public class Response {

	@ApiModelProperty(value = "HTTP Status Code")
	private int status;
	@ApiModelProperty(value = "HTTP Message")
	private String message;

	public Response() {
		super();
	}

	public Response(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
