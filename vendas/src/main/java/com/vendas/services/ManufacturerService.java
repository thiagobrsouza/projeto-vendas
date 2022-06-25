package com.vendas.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.entities.Manufacturer;
import com.vendas.repositories.ManufacturerRepository;

@Service
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepository repository;
	
	@Transactional
	public Manufacturer create(Manufacturer manufacturer) {
		return repository.save(manufacturer);
	}

}
