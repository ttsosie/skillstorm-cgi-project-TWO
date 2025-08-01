package com.skillSTORM.warehouse_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillSTORM.warehouse_api.dtos.WarehousesDTO;
import com.skillSTORM.warehouse_api.models.Warehouses;
import com.skillSTORM.warehouse_api.services.WarehousesService;

// Reaches out to service to run logic but not the repository, that's the service's job
// This IS-A BEAN

@RestController
@RequestMapping("/warehouses")
public class WarehousesController {
	
	//Filled up with Singleton Service
	private final WarehousesService service;
	
	public WarehousesController(WarehousesService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Warehouses>> findAll() {
		return this.service.finalAll(); //not happy when changed WarehousesServices to return a response entity
	}
	
	//When user wants to look up an id like localhost:8080/warehouses/2 
	@GetMapping("/{warehouseId}")
	public ResponseEntity<Warehouses> findById(@PathVariable("warehouseId") int id){
		return this.service.findById(id);
	}
	
	//When user wants to delete a warehouse by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		return this.service.deleteById(id);
	}
	
	// If user wanted to create a warehouse 
	@PostMapping
	public ResponseEntity<Warehouses> createOne(@RequestBody WarehousesDTO dto){
		return this.service.createOne(dto);
	}
	
	// If user wanted to update a warehouse id
	@PutMapping("/{id}")
	public ResponseEntity<Warehouses> updateOne(@PathVariable int id, @RequestBody WarehousesDTO dto){
		return this.service.updateOne(id, dto);
	}

}
