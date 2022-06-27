package com.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByCpf(String cpf);

}
