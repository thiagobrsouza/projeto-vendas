package com.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(readOnly = true)
	public Manufacturer getById(Long id) {
		Optional<Manufacturer> manufacturer = repository.findById(id);
		return manufacturer.get();
	}

}
