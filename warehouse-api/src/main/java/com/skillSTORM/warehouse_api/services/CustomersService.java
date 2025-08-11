package com.skillSTORM.warehouse_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillSTORM.warehouse_api.dtos.CustomersDTO;
import com.skillSTORM.warehouse_api.models.Customers;
import com.skillSTORM.warehouse_api.repositories.CustomersRepository;


// Injects the repository to use to run the service methods
// This IS-A BEAN

@Service
public class CustomersService {
private final CustomersRepository repo;
	
	// Constructor Injection
	// Populates the repository variable when Bean is instantiated so repository is final
	@Autowired
	public CustomersService(CustomersRepository repo) {
		this.repo = repo;
	}
	
	// To get back all of the existing Customers
	public ResponseEntity<Iterable<Customers>> findAll(){
		Iterable<Customers> customers = this.repo.findAll();
		if (!customers.iterator().hasNext())
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
		
		return ResponseEntity.ok(customers);
	}
	
	// To get a specific customer by id
	public ResponseEntity<Customers> findById(int id){
		Optional<Customers> customer = this.repo.findById(id);
		
		if(customer.isPresent()) { // Using Optional gives the isPresent or isEmpty option from CRUD
			return ResponseEntity.ok(customer.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// If user needed to delete a customer by a specific id
	public ResponseEntity<Void> deleteById(int id){
		this.repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// If user wanted to create a customer - id(don't care about)
	public ResponseEntity<Customers> createOne(CustomersDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.repo.save(
						new Customers(
						0,
						dto.firstName(), 
						dto.lastName(), 
						dto.email(), 
						dto.state())));
	}
	
	// If user wanted to update a customer id
	public ResponseEntity<Customers> updateOne(int id, CustomersDTO dto){
		if(this.repo.existsById(id)) {
			return ResponseEntity.ok(this.repo.save(
					new Customers(
					0, 
					dto.firstName(), 
					dto.lastName(), 
					dto.email(), 
					dto.state())));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
