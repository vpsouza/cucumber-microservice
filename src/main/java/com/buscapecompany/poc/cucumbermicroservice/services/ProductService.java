package com.buscapecompany.poc.cucumbermicroservice.services;

import java.util.List;

import com.buscapecompany.poc.cucumbermicroservice.model.Product;

public interface ProductService {

	List<Product> findAll();
	Product findOne(long id);
	Product save(Product product);
	void delete(Product product);
	List<Product> findChildrenByParent(Product parent);
	List<Product> findByParentIsNull();
}
