package com.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Product findByName(String name);

}
