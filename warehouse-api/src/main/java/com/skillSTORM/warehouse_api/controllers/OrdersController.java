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

import com.skillSTORM.warehouse_api.dtos.OrdersDTO;
import com.skillSTORM.warehouse_api.models.Orders;
import com.skillSTORM.warehouse_api.services.OrdersService;

//Reaches out to service to run logic but not the repository, that's the service's job
//This IS-A BEAN

@RestController
@RequestMapping("/orders")
public class OrdersController {
	//Filled up with Singleton Service
	private final OrdersService service;
	
	public OrdersController(OrdersService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Orders>> findAll() {
		return this.service.finalAll(); //not happy when changed OrdersServices to return a response entity
	}
	
	//When user wants to look up an id like localhost:8080/products/2 
	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> findById(@PathVariable("orderId") int id){
		return this.service.findById(id);
	}
	
	//When user wants to delete a order by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		return this.service.deleteById(id);
	}
	
	// If user wanted to create an order 
	@PostMapping
	public ResponseEntity<Orders> createOne(@RequestBody OrdersDTO dto){
		return this.service.createOne(dto);
	}
	
	// If user wanted to update an order id
	@PutMapping("/{id}")
	public ResponseEntity<Orders> updateOne(@PathVariable int id, @RequestBody OrdersDTO dto){
		return this.service.updateOne(id, dto);
}
}
