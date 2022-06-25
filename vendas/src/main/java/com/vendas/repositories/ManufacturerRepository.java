package com.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.entities.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
