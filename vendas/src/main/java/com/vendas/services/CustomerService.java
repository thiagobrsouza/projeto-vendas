package com.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.entities.Customer;
import com.vendas.repositories.CustomerRepository;
import com.vendas.services.exceptions.DataIntegrityException;
import com.vendas.services.exceptions.EntityNotFoundException;
import com.vendas.services.exceptions.EntityRelationshipException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Transactional
	public Customer create(Customer customer) {
		Customer exists = repository.findByCpf(customer.getCpf());
		if (exists != null) {
			throw new DataIntegrityException("Cliente já registrado");
		}
		return repository.save(customer);
	}

	@Transactional(readOnly = true)
	public Customer getById(Long id) {
		Customer customer = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado. Id: " + id));
		return customer;
	}

	@Transactional(readOnly = true)
	public List<Customer> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Customer update(Long id, Customer customer) {
		Customer customerFounded = getById(id);
		Customer exists = repository.findByCpf(customer.getCpf());
		if (exists != null && exists.getId() != customerFounded.getId()) {
			throw new DataIntegrityException("Cliente já cadastrado!");
		}
		customerFounded.setName(customer.getName());
		customerFounded.setCpf(customer.getCpf());
		customerFounded.setEmail(customer.getEmail());
		repository.save(customerFounded);
		return customerFounded;
	}

	public void delete(Long id) {
		getById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityRelationshipException("Objeto não pode ser removido pois há associações à ele!");
		}
	}

}
