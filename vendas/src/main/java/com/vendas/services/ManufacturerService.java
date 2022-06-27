package com.vendas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendas.entities.Manufacturer;
import com.vendas.repositories.ManufacturerRepository;
import com.vendas.services.exceptions.DataIntegrityException;
import com.vendas.services.exceptions.EntityNotFoundException;
import com.vendas.services.exceptions.EntityRelationshipException;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepository repository;

	@Transactional
	public Manufacturer create(Manufacturer manufacturer) {
		Manufacturer exists = repository.findByName(manufacturer.getName());
		if (exists != null) {
			throw new DataIntegrityException("Fornecedor já registrado");
		}
		return repository.save(manufacturer);
	}

	@Transactional(readOnly = true)
	public Manufacturer getById(Long id) {
		Manufacturer manufacturer = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado. Id: " + id));
		return manufacturer;
	}

	@Transactional(readOnly = true)
	public List<Manufacturer> getAll() {
		return repository.findAll();
	}

	@Transactional
	public Manufacturer update(Long id, Manufacturer manufacturer) {
		Manufacturer manufacturerFounded = getById(id);
		Manufacturer exists = repository.findByName(manufacturer.getName());
		if (exists != null && exists.getId() != manufacturerFounded.getId()) {
			throw new DataIntegrityException("Fornecedor já cadastrado!");
		}
		manufacturerFounded.setName(manufacturer.getName());
		repository.save(manufacturerFounded);
		return manufacturerFounded;
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
