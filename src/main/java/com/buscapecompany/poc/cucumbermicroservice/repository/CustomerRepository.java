package com.buscapecompany.poc.cucumbermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buscapecompany.poc.cucumbermicroservice.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
