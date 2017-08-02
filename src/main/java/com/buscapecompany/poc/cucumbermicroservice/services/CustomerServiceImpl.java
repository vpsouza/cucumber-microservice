package com.buscapecompany.poc.cucumbermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buscapecompany.poc.cucumbermicroservice.model.Customer;
import com.buscapecompany.poc.cucumbermicroservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private CustomerRepository repository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Customer findOne(long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		repository.delete(customer);
	}

}
