package com.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.entities.Product;
import com.vendas.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		product = service.create(product);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		Product product = service.getById(id);
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> list = service.getAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		Product productFounded = service.update(id, product);
		return ResponseEntity.ok().body(productFounded);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
