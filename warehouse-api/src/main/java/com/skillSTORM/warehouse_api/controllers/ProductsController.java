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

import com.skillSTORM.warehouse_api.dtos.ProductsDTO;
import com.skillSTORM.warehouse_api.models.Products;
import com.skillSTORM.warehouse_api.services.ProductsService;

//Reaches out to service to run logic but not the repository, that's the service's job
//This IS-A BEAN

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	//Filled up with Singleton Service
		private final ProductsService service;
		
		public ProductsController(ProductsService service) {
			this.service = service;
		}
		
		@GetMapping
		public ResponseEntity<Iterable<Products>> findAll() {
			return this.service.findAll(); //not happy when changed ProductsServices to return a response entity
		}
		
		//When user wants to look up an id like localhost:8080/products/2 
		@GetMapping("/{productId}")
		public ResponseEntity<Products> findById(@PathVariable("productId") int id){
			return this.service.findById(id);
		}
		
		//When user wants to delete a product by id
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteById(@PathVariable int id){
			return this.service.deleteById(id);
		}
		
		// If user wanted to create a product 
		@PostMapping
		public ResponseEntity<Products> createOne(@RequestBody ProductsDTO dto){
			return this.service.createOne(dto);
		}
		
		// If user wanted to update a product id
		@PutMapping("/{id}")
		public ResponseEntity<Products> updateOne(@PathVariable int id, @RequestBody ProductsDTO dto){
			return this.service.updateOne(id, dto);
	}

}
