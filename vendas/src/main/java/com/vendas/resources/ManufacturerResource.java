package com.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.entities.Manufacturer;
import com.vendas.services.ManufacturerService;

@RestController
@RequestMapping(value = "/manufacturers")
public class ManufacturerResource {
	
	@Autowired
	private ManufacturerService service;
	
	@PostMapping
	public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer) {
		manufacturer = service.create(manufacturer);
		return ResponseEntity.ok().body(manufacturer);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Manufacturer> getById(@PathVariable Long id) {
		Manufacturer manufacturer = service.getById(id);
		return ResponseEntity.ok().body(manufacturer);
	}
	
	@GetMapping
	public ResponseEntity<List<Manufacturer>> getAll() {
		List<Manufacturer> list = service.getAll();
		return ResponseEntity.ok().body(list);
	}

}
