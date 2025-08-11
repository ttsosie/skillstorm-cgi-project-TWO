package com.skillSTORM.warehouse_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillSTORM.warehouse_api.dtos.ProductsDTO;
import com.skillSTORM.warehouse_api.models.Products;
import com.skillSTORM.warehouse_api.repositories.ProductsRepository;


// Injects the repository to use to run the service methods
// This IS-A BEAN

@Service
public class ProductsService {
	
private final ProductsRepository repo;
	
	// Constructor Injection
	// Populates the repository variable when Bean is instantiated so repository is final
	@Autowired
	public ProductsService(ProductsRepository repo) {
		this.repo = repo;
	}
	
	// To get back all of the existing warehouses
	public ResponseEntity<Iterable<Products>> findAll(){
		Iterable<Products> products = this.repo.findAll();
		if (!products.iterator().hasNext())
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								 .body(null);
		
		return ResponseEntity.ok(products);
	}
	
	// To get a specific product by id
	public ResponseEntity<Products> findById(int id){
		Optional<Products> product = this.repo.findById(id);
		
		if(product.isPresent()) { // Using Optional gives the isPresent or isEmpty option from CRUD
			return ResponseEntity.ok(product.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	// If user needed to delete a product by a specific id
	public ResponseEntity<Void> deleteById(int id){
		this.repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// If user wanted to create a product - id(don't care about), name, price, details, warehouseId (where it's stored), quantity
	public ResponseEntity<Products> createOne(ProductsDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.repo.save(
						new Products(
						0,
						dto.productName(), 
						dto.price(), 
						dto.details(), 
						dto.warehouseId(), 
						dto.quantity())));
	}
	
	// If user wanted to update a product id
	public ResponseEntity<Products> updateOne(int id, ProductsDTO dto){
		if(this.repo.existsById(id)) {
			return ResponseEntity.ok(this.repo.save(
					new Products(
					0, 
					dto.productName(), 
					dto.price(), 
					dto.details(), 
					dto.warehouseId(), 
					dto.quantity())));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
