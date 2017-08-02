package com.buscapecompany.poc.cucumbermicroservice.services;

import java.util.List;

import com.buscapecompany.poc.cucumbermicroservice.model.Customer;

public interface CustomerService {

	List<Customer> findAll();
	Customer findOne(long id);
	Customer save(Customer Customer);
	void delete(Customer Customer);
}
