package com.skillSTORM.warehouse_api.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillSTORM.warehouse_api.dtos.OrdersDTO;
import com.skillSTORM.warehouse_api.models.Orders;
import com.skillSTORM.warehouse_api.repositories.OrdersRepository;


// Injects the repository to use to run the service methods
// This IS-A BEAN

@Service
public class OrdersService {
private final OrdersRepository repo;
	
	// Constructor Injection
	// Populates the repository variable when Bean is instantiated so repository is final
	@Autowired
	public OrdersService(OrdersRepository repo) {
		this.repo = repo;
	}
	
	// To get back all of the existing Orders
	public ResponseEntity<Iterable<Orders>> findAll(){
		Iterable<Orders> orders = this.repo.findAll();
		if (!orders.iterator().hasNext())
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
		
		return ResponseEntity.ok(orders);
	}
	
	// To get a specific order by id
	public ResponseEntity<Orders> findById(int id){
		Optional<Orders> order = this.repo.findById(id);
		
		if(order.isPresent()) { // Using Optional gives the isPresent or isEmpty option from CRUD
			return ResponseEntity.ok(order.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// If user needed to delete a order by a specific id
	public ResponseEntity<Void> deleteById(int id){
		this.repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// If user wanted to create a order - id(don't care about)
	public ResponseEntity<Orders> createOne(OrdersDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.repo.save(
						new Orders(
						0,
						dto.customerId(),
						dto.productId(),
						dto.date())));
	}
	
	// If user wanted to update a order id
	public ResponseEntity<Orders> updateOne(int id, OrdersDTO dto){
		if(this.repo.existsById(id)) {
			return ResponseEntity.ok(this.repo.save(
					new Orders(
						0,
						dto.customerId(),
						dto.productId(),
						dto.date())));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
