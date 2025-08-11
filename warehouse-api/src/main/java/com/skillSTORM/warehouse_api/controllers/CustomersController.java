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

import com.skillSTORM.warehouse_api.dtos.CustomersDTO;
import com.skillSTORM.warehouse_api.models.Customers;
import com.skillSTORM.warehouse_api.services.CustomersService;

//Reaches out to service to run logic but not the repository, that's the service's job
//This IS-A BEAN

@RestController
@RequestMapping("/customers")
public class CustomersController {
	//Filled up with Singleton Service
	private final CustomersService service;
	
	public CustomersController(CustomersService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Customers>> findAll() {
		return this.service.finalAll(); //not happy when changed CustomersServices to return a response entity
	}
	
	//When user wants to look up an id like localhost:8080/products/2 
	@GetMapping("/{customerId}")
	public ResponseEntity<Customers> findById(@PathVariable("customerId") int id){
		return this.service.findById(id);
	}
	
	//When user wants to delete a customer by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		return this.service.deleteById(id);
	}
	
	// If user wanted to create a customer 
	@PostMapping
	public ResponseEntity<Customers> createOne(@RequestBody CustomersDTO dto){
		return this.service.createOne(dto);
	}
	
	// If user wanted to update a customer id
	@PutMapping("/{id}")
	public ResponseEntity<Customers> updateOne(@PathVariable int id, @RequestBody CustomersDTO dto){
		return this.service.updateOne(id, dto);
}
}
