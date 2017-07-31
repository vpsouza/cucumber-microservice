package com.buscapecompany.poc.cucumbermicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buscapecompany.poc.cucumbermicroservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findChildrenByParent(Product parent);
	
	public List<Product> findByParentIsNull();
}
