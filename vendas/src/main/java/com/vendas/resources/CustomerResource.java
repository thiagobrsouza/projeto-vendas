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

import com.vendas.entities.Customer;
import com.vendas.services.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		customer = service.create(customer);
		return ResponseEntity.ok().body(customer);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		Customer customer = service.getById(id);
		return ResponseEntity.ok().body(customer);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		List<Customer> list = service.getAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
		Customer customerFounded = service.update(id, customer);
		return ResponseEntity.ok().body(customerFounded);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
