package com.buscapecompany.poc.cucumbermicroservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscapecompany.poc.cucumbermicroservice.exception.ProductException;
import com.buscapecompany.poc.cucumbermicroservice.model.Product;
import com.buscapecompany.poc.cucumbermicroservice.model.Response;
import com.buscapecompany.poc.cucumbermicroservice.repository.ProductRepository;
import com.buscapecompany.poc.cucumbermicroservice.validation.ProductValidation;
import com.buscapecompany.poc.cucumbermicroservice.validation.ValidationResult;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository repository;

	@GetMapping
	public ResponseEntity<List<Product>> getAllSumarized() {
		logger.info("returning all summarized products...");

		return new ResponseEntity<List<Product>>(repository.findByParentIsNull(), HttpStatus.OK);
	}

	@GetMapping("/{id:.+}")
	public ResponseEntity<Product> findOneSumarized(@PathVariable("id") long id) throws ProductException {
		logger.info("returning a summarized product by id...");

		Product product = repository.findOne(id);

		ValidationResult validationResult = ProductValidation.RestValidation.isProductExists().apply(product);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/{id:.+}/children")
	public ResponseEntity<List<Product>> getChildsById(@PathVariable("id") long id) throws ProductException {
		logger.info("returning a product by id...");

		Product product = repository.findOne(id);

		ValidationResult validationResult = ProductValidation.RestValidation.isProductExists().apply(product);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}
		
		return new ResponseEntity<List<Product>>(repository.findChildrenByParent(product), HttpStatus.OK);
	}

	@DeleteMapping("/{id:.+}")
	public ResponseEntity<Response> delete(@PathVariable("id") long id) throws ProductException {
		logger.info("deleting a product by id...");

		Product product = repository.findOne(id);

		ValidationResult validationResult = ProductValidation.RestValidation.isProductExists().apply(product);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}

		repository.delete(product);

		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Product has been deleted!"),
				HttpStatus.OK);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> save(@RequestBody Product payload) throws ProductException {
		logger.info("product to save " + payload);

		ValidationResult validationResult = ProductValidation.RestValidation.isCreatePayloadValid().apply(payload);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}
		
		fetchChildrenWithParend(payload);

		return new ResponseEntity<Product>(repository.save(payload), HttpStatus.OK);
	}
	
	private void fetchChildrenWithParend(Product payload){
		payload.getChildren().stream().forEach(child -> child.setParent(payload));
		if(payload.getParent() != null && payload.getParent().getId() > 0){
			fetchChildrenWithParend(payload.getParent());
		}
	}

	@PatchMapping
	public ResponseEntity<Product> update(@RequestBody Product payload) throws ProductException {
		logger.info("product to update " + payload);

		ValidationResult validationResult = ProductValidation.RestValidation.isUpdatePayloadValid().apply(payload);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}

		Product product = repository.findOne(payload.getId());
		validationResult = ProductValidation.RestValidation.isProductExists().apply(product);
		if (!validationResult.isValid()) {
			throw new ProductException(validationResult.getViolations().get());
		}
		
		fetchChildrenWithParend(payload);

		return new ResponseEntity<Product>(repository.save(payload), HttpStatus.OK);
	}
}
