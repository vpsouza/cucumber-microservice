package com.buscapecompany.poc.cucumbermicroservice.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buscapecompany.poc.cucumbermicroservice.model.Product;
import com.buscapecompany.poc.cucumbermicroservice.repository.ProductRepository;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository){
		this.productRepository = repository;
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findOne(long id) {
		return productRepository.findOne(id);
	}

	@Transactional
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> findChildrenByParent(Product parent) {
		// TODO Auto-generated method stub
		return productRepository.findChildrenByParent(parent);
	}

	@Override
	public List<Product> findByParentIsNull() {
		// TODO Auto-generated method stub
		return productRepository.findByParentIsNull();
	}
	
}
