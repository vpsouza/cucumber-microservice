package com.buscapecompany.poc.cucumbermicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscapecompany.poc.cucumbermicroservice.model.Customer;
import com.buscapecompany.poc.cucumbermicroservice.model.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
@Api(value="customers")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping
	@ApiOperation(value = "View a list of available customers", nickname = "findAll")
	public ResponseEntity<List<Customer>> findAll(){
		logger.info("View a list of available customers");
		logger.debug ("View a list of available customers");
		logger.error("View a list of available customers");
		logger.trace ("View a list of available customers");
		logger.warn("View a list of available customers");
		return new ResponseEntity<List<Customer>>(new ArrayList<Customer>(), HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "Create a new customer", nickname = "save")
	public ResponseEntity<Customer> save(@RequestBody Customer payload){
		return new ResponseEntity<Customer>(new Customer(1L, "Vinicius", "Piedade", "de Souza", 31), HttpStatus.OK);
	}
	
	@PatchMapping
	@ApiOperation(value = "Update an existing customer", nickname = "update")
	public ResponseEntity<Customer> update(@RequestBody Customer payload){
		return new ResponseEntity<Customer>(new Customer(1L, "Michel", null, "Temer", 31), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id:.+}")
	@ApiOperation(value = "Delete an existing customer", nickname = "delete")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "Customer Id", required = false, dataType = "long", paramType = "query")
      })
	public ResponseEntity<Response> delete(@PathVariable Long id){
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Customer has been deleted"), HttpStatus.OK);
	}
	
}
