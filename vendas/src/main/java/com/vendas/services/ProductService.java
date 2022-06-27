package com.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.entities.Product;
import com.vendas.repositories.ProductRepository;
import com.vendas.services.exceptions.DataIntegrityViolationException;
import com.vendas.services.exceptions.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional
	public Product create(Product product) {
		Product exists = repository.findByName(product.getName());
		if (exists != null) {
			throw new DataIntegrityViolationException("Produto já cadastrado!");
		}
		return repository.save(product);
	}
	
	@Transactional(readOnly = true)
	public Product getById(Long id) {
		Product product = repository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Produto não encontrado. Id: " + id));
		return product;
	}
	
	@Transactional(readOnly = true)
	public List<Product> getAll() {
		return repository.findAll();
	}
	
	@Transactional
	public Product update(Long id, Product product) {
		Product productFounded = getById(id);
		Product exists = repository.findByName(product.getName());
		if (exists != null && exists.getId() != productFounded.getId()) {
			throw new DataIntegrityViolationException("Produto já cadastrado!");
		}
		productFounded.setName(product.getName());
		repository.save(productFounded);
		return productFounded;
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
