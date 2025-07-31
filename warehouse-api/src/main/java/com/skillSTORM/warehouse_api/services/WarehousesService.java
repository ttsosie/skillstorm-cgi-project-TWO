package com.skillSTORM.warehouse_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillSTORM.warehouse_api.models.Warehouses;
import com.skillSTORM.warehouse_api.repositories.WarehousesRepository;

// Injects the repository to use to run the service methods
// This IS-A BEAN

@Service
public class WarehousesService {
	
	private final WarehousesRepository repo;
	
	// Constructor Injection
	// Populates the repository variable when Bean is instantiated so repository is final
	@Autowired
	public WarehousesService(WarehousesRepository repo) {
		this.repo = repo;
	}
	
	// To get back all of the existing warehouses
	public ResponseEntity<Iterable<Warehouses>> finalAll(){
		Iterable<Warehouses> warehouses = this.repo.findAll();
		if (!warehouses.iterator().hasNext())
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
		
		return ResponseEntity.ok(warehouses);
	}
	
	// To get a specific warehouse by id
	public ResponseEntity<Warehouses> findById(int id){
		Optional<Warehouses> warehouse = this.repo.findById(id);
		
		if(warehouse.isPresent()) { // Using Optional gives the isPresent or isEmpty option from CRUD
			return ResponseEntity.ok(warehouse.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// If user needed to delete a warehouse by a specific id
	public ResponseEntity<Void> deleteById(int id){
		this.repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
